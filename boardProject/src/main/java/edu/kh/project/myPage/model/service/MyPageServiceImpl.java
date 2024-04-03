package edu.kh.project.myPage.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	@Autowired
	//	public MyPageServiceImpl(MyPageMapper mapper) {
	//		this.mapper = mapper;
	//	}
	
	
	
}
