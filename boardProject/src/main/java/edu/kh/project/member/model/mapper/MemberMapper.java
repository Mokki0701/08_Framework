package edu.kh.project.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 로그인 SQL 실행
	 * @param memberEmail
	 * @return loginMember
	 */
	public Member login(String memberEmail);

	/** 회원 가입
	 * @param inputMember
	 * @return result
	 */
	public int signup(Member inputMember);

	/** 이메일 중복 검사
	 * @param memberEmail
	 * @return 0,1
	 */
	public int checkEmail(String memberEmail);

	/** 닉네임 중복 검사
	 * @param memberNickname
	 * @return 0,1
	 */
	public int checkNickname(String memberNickname);
	
}
