package edu.kh.project.freeBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.freeBoard.model.service.FreeBoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("freeBoard")
public class FreeBoardController {
	
	private final FreeBoardService service;
	
	@GetMapping("{freeBoardCode:[0-9]+}")
	public String selectBoardList(
			@PathVariable("freeBoardCode") int freeBoardCode,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			Model model
			) {
		
		
		return "board/boardList2";
	}

}
