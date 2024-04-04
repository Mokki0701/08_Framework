package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.main.model.service.MainService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller // bean 등록
public class MainController {
	
	private final MainService service;
	
	@RequestMapping("/") // "/" 요청 매핑(method 가리지 않음)
	public String mainPage() {
		
		
		return "common/main";
	}
	
	@ResponseBody
	@PutMapping("resetPw")
	public int resetPw(
			@RequestBody int inputNo
			) {
		
		return service.resetPw(inputNo);
	}
	
	
	@ResponseBody
	@PutMapping("restoreMember")
	public int restoreMember(
			@RequestBody int inputNo
			) {
		
		return service.restoreMember(inputNo);
	}
	
	
	@ResponseBody
	@DeleteMapping("deleteMember")
	public int deleteMember(
			@RequestBody int inputNo
			) {
		return service.deleteMember(inputNo);
	}
	
	
	// LoginFilter -> loginError 리다이렉트
	// -> message를 만들어서 메인 페이지로 리다이렉트
	
	@GetMapping("loginError")
	public String loginError(
			RedirectAttributes ra
			) {
		ra.addFlashAttribute("message", "로그인 후 이용해 주세요");
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
