package edu.kh.project.freeBoard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FreeBoard {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int readCount;
	private String boardDelFl;
	private int boardCode;
	private int memberNo;
	
	// MEMBER 테이블 조인
	private String memberNickname;
	
	// 목록 조회 시 상관 서브쿼리
	private int commentCount;
	private int likeCount;
	
	// 게시글 작성자 프로필 이미지
	private String profileImg;
	
	// 게시글 목록 썸네일
	private String thumbnail;
	
}
