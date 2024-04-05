package edu.kh.project.myPage.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.DTO.UploadFile;

public interface MyPageService  {

	int updateInfo(Member inputMember, String[] memberAddress);

	int changePw(String currentPw, String newPw, int memberNo);

	int secession(String memberPw, int memberNo);

	/** 파일 업로드 테스트1
	 * @param uploadFile
	 * @return path
	 */
	String fileUpload1(MultipartFile uploadFile) throws IllegalStateException, IOException;

	/** 파일 업로드 테스트2(+DB)
	 * @param uploadFile
	 * @param memberNo
	 * @return result
	 */
	int fileUpload2(MultipartFile uploadFile, int memberNo) throws IllegalStateException, IOException;

	/** 파일 목록 조회
	 * @return fileList
	 */
	List<UploadFile> fileList();

	/** 여러 파일 업로드
	 * @param aaaList
	 * @param bbbList
	 * @param memberNo
	 * @return result
	 */
	int fileUpload3(List<MultipartFile> aaaList, List<MultipartFile> bbbList, int memberNo) throws IllegalStateException, IOException;

	
	/** 프로파일 업로드
	 * @param profileImg
	 * @param loginMember
	 * @return
	 */
	int inputProfile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
