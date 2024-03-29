package edu.kh.project.member.model.service;

import edu.kh.project.member.model.dto.Member;

public interface MemberService {

	Member login(Member inputMember);

	int signup(Member inputMember, String[] memberAddress);

	int checkEmail(String memberEmail);

	int checkNickname(String memberNickname);

}
