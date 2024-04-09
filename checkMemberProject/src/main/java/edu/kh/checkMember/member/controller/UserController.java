package edu.kh.checkMember.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.checkMember.member.model.dto.User;
import edu.kh.checkMember.member.model.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping("member/search")
	private String searchMember(
			@RequestParam("memberId") String memberId,
			Model model
			) {
		
		User member = service.searchMember(memberId);
		
		model.addAttribute("member", member);
		
		String path = null;
		
		if(member != null) path = "searchSuccess";
		else 			   path = "searchFail";
		
		return path;
	}

}
