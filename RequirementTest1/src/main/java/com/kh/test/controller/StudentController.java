package com.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.test.model.dto.Student;
import com.kh.test.model.service.StudentService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("student")
public class StudentController {

	private StudentService service;
	
	@PostMapping("add")
	@ResponseBody
	private int addStudent(
			@RequestBody Student student
			) {
				
		return service.addStrudnet(student);
	}
}
