package edu.kh.project.comment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Comment;

@Mapper
public interface CommentMapper {

	List<Comment> select(int boardNo);

	int insert(Comment comment);

	int delete(int commentNo);

	int update(Comment comment);

}
