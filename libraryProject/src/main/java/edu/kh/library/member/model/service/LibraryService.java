package edu.kh.library.member.model.service;

import java.util.List;

import edu.kh.library.member.model.dto.Book;

public interface LibraryService {

	int enrollBook(Book inputBook);

	List<Book> selectList();

	List<Book> checkBook(String inputContext);

	void removeBook(int bookNo);

	void updatePrice(Book book);

}
