package edu.kh.library.member.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.library.member.model.dto.Book;
import edu.kh.library.member.model.mapper.LibraryMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

	private final LibraryMapper mapper;

	@Override
	public int enrollBook(Book inputBook) {
		
		return mapper.enrollBook(inputBook);
	}

	@Override
	public List<Book> selectList() {
		
		return mapper.selectList();
	}

	@Override
	public List<Book> checkBook(String inputContext) {
		return mapper.checkBook(inputContext);
	}

	@Override
	public void removeBook(int bookNo) {

		mapper.removeBook(bookNo);
	}

	@Override
	public void updatePrice(Book book) {

		mapper.updatePrice(book);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
