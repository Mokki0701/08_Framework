package edu.kh.project.main.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.main.model.mapper.MainMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
	
	private final MainMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

	@Override
	public int resetPw(int inputNo) {
		
		String pw = "1234";
		
		String encPw = bcrypt.encode(pw);
		
		// Wrapper Class -> Auto Boxing (int -> Integer)
		Map<String, Object> map = new HashMap<>();
		
		map.put("inputNo", inputNo);
		map.put("encPw", encPw);
		
		return mapper.resetPw(map);
	}

	@Override
	public int restoreMember(int inputNo) {
		return mapper.restoreMember(inputNo);
	}

	@Override
	public int deleteMember(int inputNo) {
		return mapper.deleteMember(inputNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
