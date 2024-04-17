package edu.kh.project.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	/** 게시글 작성
	 * @param inputBoard
	 * @return result
	 */
	int boardInsert(Board inputBoard);

	/** 게시글 이미지 모두 삽입
	 * @param uploadList 
	 * @return result
	 */
	int insertImageUploadList(List<BoardImg> uploadList);

	int boardDelete(Board board);

	/** 게시글 제목, 내용 수정
	 * @param inputBoard
	 * @return result
	 */
	int boardUpdate(Board inputBoard);

	/** 게시글 삭제된 이미지 제거
	 * @param map
	 * @return result
	 */
	int deleteImage(Map<String, Object> map);

	/** 게시글 이미지 수정
	 * @param img
	 * @return result
	 */
	int updateImage(BoardImg img);

	/** 기존에 없던 이미지 추가
	 * @param img
	 * @return result
	 */
	int insertImage(BoardImg img);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
