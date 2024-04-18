package edu.kh.project.comment.model.service;

import java.util.List;

import edu.kh.project.board.model.dto.Comment;

public interface CommentService {

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return commentList
	 */
	List<Comment> select(int boardNo);

	int insert(Comment comment);

	int delete(int commentNo);

	int update(Comment comment);

}
