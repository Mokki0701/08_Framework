package bradyes.page.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	private String mainPage() {
		
		return "common/main";
	}
	
}
