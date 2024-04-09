package edu.kh.checkMember.member.model.service;

import org.springframework.stereotype.Service;

import edu.kh.checkMember.member.model.dto.User;
import edu.kh.checkMember.member.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper mapper;

	@Override
	public User searchMember(String memberId) {
		
		User member = mapper.searchMember(memberId);
		
		if(member == null) return null;
		
		return member;
	}

}
