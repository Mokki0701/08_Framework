package edu.kh.project.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	List<Map<String, Object>> selectBoardTypeList();

	/** 게시글 수를 조회
	 * @param boardCode
	 * @return listCount
	 */
	int getListCount(int boardCode);

	/** 특정 게시판의 지정된 페이지 목록 조회
	 * @param boardCode
	 * @param rowBounds
	 * @return boardList
	 */
	List<Board> selectBoardList(int boardCode, RowBounds rowBounds);

	/** 특정 게시판 상세 조회
	 * @param map
	 * @return board
	 */
	Board selectOne(Map<String, Integer> map);

	/** 좋아요 해제 (DELETE)
	 * @param map
	 * @return result
	 */
	int deleteBoardLike(Map<String, Integer> map);

	/** 좋아요 체크 (INSERT)
	 * @param map
	 * @return result
	 */
	int insertBoardLike(Map<String, Integer> map);

	/** 좋아요 수 조회
	 * @param temp
	 * @return result
	 */
	int selectLikeCount(int temp);

	/** 조회 수 1 증가
	 * @param boardNo
	 * @return reusult
	 */
	int updateReadCount(int boardNo);

	/** 조회 수 조회
	 * @param boardNo
	 * @return count
	 */
	int selectReadCount(int boardNo);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
