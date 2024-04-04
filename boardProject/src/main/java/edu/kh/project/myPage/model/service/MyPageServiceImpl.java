package edu.kh.project.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		// 입력된 주소가 있을 경우
		// memberAddress를 A^^^B^^^C 형태로 가공
		
		// 주소 입력 X -> inputMember.getMemberAddress() -> ",,"
		
		if(inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null);
		}else {
			inputMember.setMemberAddress(String.join("^^^", memberAddress));
		}
		
		return mapper.updateInfo(inputMember);
	}

	@Override
	public int changePw(String currentPw, String newPw, int memberNo) {
		
		String nowPw = mapper.getPw(memberNo);
			
		if(bcrypt.matches(currentPw, nowPw)) {
			// 조회 결과 같은 경우
			String newBcryptPw = bcrypt.encode(newPw);
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("newBcryptPw", newBcryptPw);
			map.put("memberNo", memberNo);
			
			int result = mapper.changePw(map); // 근데 만약 업데이트가 실패하면 0이 가는데 이것도 
			if(result == 0) {
				return 2; // 만약 실패하면 2를 반환
			}
			
			return 1; // 새로운 비밀번호 삽입 성공
			
		}
		
		return 0;
	}

	@Override
	public int secession(String memberPw, int memberNo) {
		
		String nowPw = mapper.getPw(memberNo);
		
		if(bcrypt.matches(memberPw, nowPw)) return mapper.secession(memberNo);
		
		return 0;
	}

	// 파일 업로드 테스트1
	@Override
	public String fileUpload1(MultipartFile uploadFile) throws IllegalStateException, IOException {
	
		// MultipartFile 이 제공하는 메서드
		// - getSize() : 파일 크기
		// - isEmpty() : 업로드한 파일이 없을 경우 경우 true
		// - getOriginalFileName() : 원본 파일 명
		// - transferTo(경로) : 
		//    메모리 또는 임시 저장 경로에 업로드된
		//	  파일을 원하는 경로에 전송 (서버 어떤 폴더에 저장할지 지정)
		if(uploadFile.isEmpty()) return null; // 업로드한 파일이 없을 경우
		
		// 업로드한 파일이 있을 경우
		// C:\\uploadFiles\\test\\파일명 으로 서버에 저장
		uploadFile.transferTo(new File("C:\\uploadFiles\\test\\" + uploadFile.getOriginalFilename()));
		
		// 웹에서 해당 파일에 접근할 수 있는 경로를 반환
		
		// 서버 : C:\\uploadFiles\\test\\a.jpg
		// 웹 접근 주소 : /myPage/file/a.jpg
		return "/myPage/file/" + uploadFile.getOriginalFilename();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	@Autowired
	//	public MyPageServiceImpl(MyPageMapper mapper) {
	//		this.mapper = mapper;
	//	}
	
	
	
}
