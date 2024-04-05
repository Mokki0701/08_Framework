package edu.kh.project.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.DTO.UploadFile;

@Mapper
public interface MyPageMapper {

	int updateInfo(Member inputMember);

	String getPw(int memberNo);

	int changePw(Map<String, Object> map);

	int secession(int memberNo);

	/** 파일 정보를 DB에 삽입
	 * @param uf
	 * @return result
	 */
	int insertUploadFile(UploadFile uf);
	
	List<UploadFile> fileList();

	int inputProfile(Member member);

	

}
