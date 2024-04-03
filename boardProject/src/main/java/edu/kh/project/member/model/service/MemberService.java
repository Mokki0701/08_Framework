package edu.kh.project.member.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.member.model.dto.Member;

public interface MemberService {

	Member login(Member inputMember);

	int signup(Member inputMember, String[] memberAddress);

	int checkEmail(String memberEmail);

	int checkNickname(String memberNickname);

	Member quickLogin(String email);

	List<Member> checkMemberList();

	String findId(Map<String, String> map);

	String sendEmail(String signup ,String email);



}
