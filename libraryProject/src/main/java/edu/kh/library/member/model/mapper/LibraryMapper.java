package edu.kh.library.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.library.member.model.dto.Book;

@Mapper
public interface LibraryMapper {

	int enrollBook(Book inputBook);

	List<Book> selectList();

	List<Book> checkBook(String inputContext);

	void removeBook(int bookNo);

	void updatePrice(Book book);

}
