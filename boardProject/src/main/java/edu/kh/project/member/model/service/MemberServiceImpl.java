package edu.kh.project.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.mapper.MemberMapper;
import jakarta.mail.internet.MimeMessage;

@Service	// 비즈니스 로직 처리 역할 + Bean 등록
@Transactional(rollbackFor = Exception.class) // (AOP 기반 기술)
public class MemberServiceImpl implements MemberService {
	
	// 등록된 bean 중에서 같은 타입 또는 상속관계인 bean을
	// 자동으로 의존성 주입(DI)
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	// Bcrype 암호화 객체 의존성 주입(SecurityConfig 참고)
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Member login(Member inputMember) {
		
		
		// 테스트(디버그 모드)
		// bcrypt.encode(문자열) : 문자열을 암호화하여 반환
		String bcryptPassword = bcrypt.encode(inputMember.getMemberPw());
		
		
		// 1. 이메일이 일치하면서 탈퇴하지 않은 회원 조회
		Member loginMember = mapper.login(inputMember.getMemberEmail());
		
		// 2. 만약에 일치하는 이메일이 없어서 조회 결과가 null인 경우
		if(loginMember == null) return null;
		
		// 3. 입력 받은 비밀번호(inputMember.getMemberPw() (평문)) 와
		//	  암호회된 비밀번호(loginMember.getMemberPw())
		//    두 비밀번호가 일치하는지 확인
		
		// 일치하지 않으면
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
		
		// 로그인 결과에서 비밀번호 제거
		loginMember.setMemberPw(null);
		
		return loginMember;
	}

	// 회원 가입 서비스
	@Override
	public int signup(Member inputMember, String[] memberAddress) {
		
		// 주소가 입력되지 않으면
		// inputMember.getMemberAddress() -> ",,"
		// memberAddress -> [,,]
		
		// 주소가 입력된 경우!
		if(!inputMember.getMemberAddress().equals(",,")) {
			
			// String.join("구분자", 배열)
			// -> 배열의 모든 요소 사이에 구분자를 추가하여
			//   하나의 문자열로 만드는 메서드
			
			// 구분자로 "^^^" 쓴 이유 : 
			// -> 주소, 상세주소에 없는 특수문자 작성
			// -> 나중에 다시 3분할 때 구분자로 이용할 예정
			String address = String.join("^^^", memberAddress);
			
			// inputMember 주소로 합쳐진 주소로 세팅
			inputMember.setMemberAddress(address);
	
		}else { // 주소 입력 X
			inputMember.setMemberAddress(null); // null 저장
		}
		// 비밀번호를 암호화 하여 inputMember에 세팅
		String encPw = bcrypt.encode(inputMember.getMemberPw()); 
		inputMember.setMemberPw(encPw);
		
		// 회원 가입 매퍼 메서드 호출
		// -> Mybatis에 의해서 자동으로 SQL이 수행됨
		//   (매퍼 메서드 호출 시 SQL에 사용할 파라미터는 1개만 전달 가능)
		return mapper.signup(inputMember);
	}

	@Override
	public int checkEmail(String memberEmail) {
		return mapper.checkEmail(memberEmail);
	}

	@Override
	public int checkNickname(String memberNickname) {
		return mapper.checkNickname(memberNickname);
	}

	@Transactional
	@Override
	public Member quickLogin(String email) {
		
		Member loginMember = mapper.login(email);
		
		if(loginMember == null) return null;
		
		loginMember.setMemberPw(null);
		
//		int temp = 1;
		
//		if(temp == 1) {
//			throw new RuntimeException("예외 던지기 테스트");
//		}
		
		
		return loginMember;
	}

	@Override
	public List<Member> checkMemberList() {
		return mapper.checkMemberList();
	}

	@Override
	public String findId(Map<String, String> map) {
		
		String email = mapper.fidId(map);
		
		if(email == null) {
			return null;
		}
		
		return email;
	}

	@Override
	public String sendEmail(String findPw,String email) {
		
		String authKey = createAuthKey();
		
		String subject = null;
		
		try {
			
			if(findPw == "findPw") {
				subject = "[boardProject] 비밀 번호 찾기 인증번호 입니다";
			}
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(loadHtml(authKey, findPw), true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo.jpg"));
			
			mailSender.send(mimeMessage);
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String, String> map = new HashMap<>();
		
		map.put("email", email);
		map.put("authKey", authKey);
		
		// 이제 안에 있나 없나 검사하고 INSERT or UPDATE를 하면될듯
		int result = mapper.updateAuthKey(map);

		if(result == 0) {
			result = mapper.insertAuthKey(map);
		}
		
		if(result == 0) return null;
		
		return authKey;
	}
	
	public String loadHtml(String authKey, String htmlName) {
		
		Context context = new Context();
		
		context.setVariable("authKey", authKey);

		return templateEngine.process("email/signup", context);
	}
	
	
	
	public String createAuthKey() {
		
		String key = "";
		for(int i = 0; i < 6; i++) {
			
			int sel1 = (int)(Math.random()*3);
			
			if(sel1 == 0) {
				int num = (int)(Math.random()* 10);
				key += num;
			}else {
				
				char ch = (char)(Math.random()*26 + 65);
				
				int sel2 = (int)(Math.random()*2);
				
				if(sel2 == 0) {
					ch = (char)(ch + ('a' - 'A'));
				}
				key += ch;	
			}	
		}
		return key;
	}

	@Override
	public int matchAuthKey(Map<String, String> map) {
		return mapper.matchAuthKey(map);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

/* BCrypt 암호화 (Spring Security 제공)
 * 
 * - 입력된 문자열(비밀번호)에 salt를 추가한 후 암호화
 *  -> 암호화 할 때 마다 결과가 다름
 *  
 * ex) 1234 입력      -> $12!asdfg
 * ex) 1234 다시 입력 -> $12!qwerz
 * 
 * - 비밀번호 확인 방법
 *  -> BCryptPasswordEncoder.matches(평문 비밀번호, 암호화된 비밀번호)
 *   -> 평문 비밀번호와
 *   	암호화된 비밀번호가 같은 경우 true
 *   	아니면 false 반환
 *   
 * * 로그인 / 비밀번호 변경 / 탈퇴 등 비밀번호가 입력되는 경우
 *   DB에 저장된 암호화된 비밀번호를 조회해서
 *   matches() 메서드로 비교해야 한다!
 */
