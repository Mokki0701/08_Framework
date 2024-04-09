package edu.kh.library.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.library.member.model.dto.Book;
import edu.kh.library.member.model.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("library")
@Slf4j
public class LibraryController {

	private final LibraryService service;
	
	@GetMapping("enrollment")
	private String enrollBook() {
		
		return "enrollBook";
	}
	
	@PostMapping("enrollment")
	private String enrollBook(
			@ModelAttribute Book inputBook,
			RedirectAttributes ra
			) {
		int result = service.enrollBook(inputBook);
		
		String message = null;
		
		if(result != 0) message = "등록 성공!!!"; 
		else 			message = "등록 실패...";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	@GetMapping("select")
	@ResponseBody
	private List<Book> selectList(){
		
		List<Book> bookList = service.selectList();
		
		if(bookList == null) return null;
		
		return bookList;
	}
	
	@GetMapping("management")
	private String management(
			Model model
			) {
		
		model.addAttribute("bookList", service.selectList());
		
		return "management";
	}
	
	@PostMapping("checkContext")
	@ResponseBody
	private List<Book> checkBook(
			@RequestBody String inputContext
			){
		List<Book> bookList = service.checkBook(inputContext);
		
		return service.checkBook(inputContext);
	}
	
	@ResponseBody
	@PostMapping("remove")
	private void removeBook(
			@RequestBody int bookNo
			) {
		
		service.removeBook(bookNo);
	}
	
	@ResponseBody
	@PostMapping("priceUpdate")
	private void priceUpdate(
			@RequestBody Book book 
			) {
		service.updatePrice(book);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
