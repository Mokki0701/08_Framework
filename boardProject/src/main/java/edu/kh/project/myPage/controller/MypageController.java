package edu.kh.project.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// loginMember 로 session에 저장되어 개인 정보가 저장되어 있음

@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

	private final MyPageService service;
	
	// 내 정보 조회/수정 화면으로 전환
	@GetMapping("info")
	public String info(
			@SessionAttribute("loginMember") Member member,
			Model model
			) {
		String memberAddress = member.getMemberAddress();
		
		// 주소가 있을 경우에만 동작
		if(memberAddress != null) {
			
			String[] arr = memberAddress.split("\\^\\^\\^");
						
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("datailAddress", arr[2]);
			
			
		}

		
		
		return "myPage/myPage-info";
	}
	
	// -------------------------------------------------------
	
	@PostMapping("info")
	public String info(
			@ModelAttribute Member inputMember,
			@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("memberAddress") String[] memberAddress,
			RedirectAttributes ra
			) {
		inputMember.setMemberNo(loginMember.getMemberNo());
		
		// 회원 정보 수정 서비스 호출
		int result = service.updateInfo(inputMember, memberAddress);
		String message = null;
		
		if(result > 0) {
			message = "회원 정보 수정 성공!!!";
			
			// loginMember는
			// 세션에 저장된 로그인된 회원 정보가 저장된 객체를
			// 참조하고 있다!!
			// -> loginMember를 수정하면
			//    세션에 저장된 로그인된 회원 정보가 수정된다!!
			
			// == 세션 데이터가 수정됨
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());
			
		}else {
			message = "회원 정보 수정 실패...";
		}
		
		ra.addFlashAttribute("message", message);
		
		
		return "redirect:info";
	}
	
	// -------------------------------------------------------
	
	@GetMapping("profile")
	public String profile() {
		return "myPage/myPage-profile";
	}
	
	// -----------------------------------------------------------
	
	@GetMapping("changePw")
	public String changePw() {
		return "myPage/myPage-changePw";
	}
	
	@PostMapping("changePw")
	public String changePw(
			@RequestParam("currentPw") String currentPw,
			@RequestParam("newPw") String newPw, // 위랑 이거를 Map<String, Object> paramMap 로 꺼낼수도 있구나
			@SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra
			) {
		int result = service.changePw(currentPw, newPw, loginMember.getMemberNo());
		String message = null;
		
		if(result == 1) {
			message = "비밀번호가 변경되었습니다.";
			ra.addFlashAttribute("message", message);
			
			return "redirect:/member/logout";
		}
		
		// 그냥 로그아웃해서 메인페이지로 가볼까?
		
		if(result == 2) {
			message = "비밀번호는 일치하지만 변경에 실패하였습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:changePw";
		}
		
		message = "현재 비밀번호가 일치하지 않습니다.";
		ra.addFlashAttribute("message", message);
		return "redirect:changePw";
	}

	// -----------------------------------------------------------
	
	@GetMapping("secession")
	public String secession() {
		return "myPage/myPage-secession";
	}
	
}







