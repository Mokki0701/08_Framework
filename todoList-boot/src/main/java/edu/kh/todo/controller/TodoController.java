package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {
	
	@Autowired // 같은 타입 Bean 의존성 주입 (DI)
	private TodoService service;
	
	@PostMapping("add")
	private String addTodo(
			@RequestParam("todoTitle") String todoTitle,
			@RequestParam("todoContent") String todoContent,
			RedirectAttributes ra
			) {
		
		// RedirectAttributes : 리다이렉트 시 값을 1회성으로 전달하는 객체
		
		// RedirectAttributes.addFlashAttribute("key", value) 형식으로 잠깐 세션에 속성 추가
		
		// [원리]
		// 응답 전 : request scope
		// redirect 중 : session scope로 이동
		// 응답 후 : reqeust scope로 복귀
		
		
		
		// 서비스 메서드 호출 후 결과 반환 받기
		int result = service.addTodo(todoTitle, todoContent);
		
		// 삽입 결과에 따라 message 값 지정
		String message = null;
		
		if(result > 0) message = "할 일 추가 성공!!!";
		else		   message = "할 일 추가 실패...";
		
		// 리다이렉트 후 1회성으로 사용할 데이터를 속성으로 추가
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	@GetMapping("detail")
	private String detailTodo(
			@RequestParam("todoNo") int todoNo,
			Model model,
			RedirectAttributes ra
			) {
		Todo todo = service.todoDetail(todoNo);
		
		String path = null;
		
		if(todo != null) { // 조회 결과 있을 경우
			
			// templates/todo/detail/html
			path = "todo/detail";
			
			model.addAttribute("todo", todo);
			
		}else { // 조회 결과가 없을 경우
			
			path = "redirect:/"; // 메인 페이지로 리다이렉트
			
			// RedirectAttribute :
			// - 리다이렉트 시 데이터를 request scope로 전달할 수 있는 객체
			ra.addFlashAttribute("message", "해당 조회 결과는 없습니다.");
		}
		
		return path;
	}
	
	// --------------------------
	
	@GetMapping("delete")
	private String deleteTodo(
			@RequestParam("todoNo") int todoNo,
			RedirectAttributes ra,
			Model model
			) {
		int result = service.deleteTodo(todoNo);
		
		String message = null;
		
		if(result > 0) message = "삭제 성공!!!";
		else 		   message = "삭제 실패...";
		
		model.addAttribute("message", message);
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	// ---------------------
	
	@GetMapping("update")
	public String UpdateTodo(
			@RequestParam("todoNo") int todoNo,
			Model model
			){
		Todo todo = service.todoDetail(todoNo);
		
		model.addAttribute("todo", todo);
		
		return "todo/update";
	}
	
	
	/** 할 일 수정
	 * @param updateTodo : 커맨드 객체(전달 받은 파라미터가 자동으로 필드에 세팅된 객체)
	 * @param ra
	 * @return
	 */
	@PostMapping("update")
	public String UpdateTodo(
			@ModelAttribute Todo updateTodo,
			RedirectAttributes ra
			) {
		
		int result = service.updateTodo(updateTodo);
		
		String message = null;
		String path = "redirect:";
		if(result > 0) {
			message = "수정 성공!!!";
			path += "/todo/detail?todoNo="+updateTodo.getTodoNo();
		}
		else {
			message = "수정 실패...";
			path += "/todo/update?todoNo="+updateTodo.getTodoNo(); // redirect는 기본적으로 get방식
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
	}
	
	@GetMapping("changeComplete")
	public String changeComplete(
			Todo todo,
			RedirectAttributes ra
			) {
		int result = service.changeComplete(todo);
		String message = null;
		
		if(result > 0) message = "변경 성공!!!";
		else		   message = "변경 실패...";
		
		ra.addFlashAttribute("message", message);
		
		
		// 현재 요청 주소 : /todo/changeComplete
		// 응답 주소	  : /todo/detail
		return "redirect:/todo/detail?todoNo="+todo.getTodoNo();
	}
	
	@GetMapping("sortNum")
	public String sortNum(RedirectAttributes ra) {
		
		int count = service.countTodo();
		
		int result = service.sortNo(count);
		String message = null;
		
		if(result == count) message = "번호 정렬 성공!!!";
		else			    message = "번호 정렬 실패...";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
