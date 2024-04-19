package com.kh.test.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.test.board.model.dto.Board;
import com.kh.test.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

	private final BoardService service;
	
	@GetMapping("search")
	private String boardSearch(
			@RequestParam("boardTitle") String boardTitle,
			Model model
			) {
		
		List<Board> boardList = new ArrayList<>();
		
		boardList = service.boardSearch(boardTitle);
		String path = null;
		
		if(!boardList.isEmpty()) {
			path = "searchSuccess";
			model.addAttribute("boardList", boardList);
		}
		else path = "searchFail";
		
		return path;
	}
	
}
