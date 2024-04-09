package edu.kh.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.customer.model.dto.Customer;
import edu.kh.customer.model.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {
	
	private final CustomerService service;
	
	@PostMapping("insert")
	public String insertCustomer(
			Model model,
			@ModelAttribute Customer customer
			) {
		service.insertCustomer(customer);
		
		model.addAttribute("message", customer.getCustomerName() + " 고객님 추가 성공!!!");
		
		return "result";
	}
	
}
