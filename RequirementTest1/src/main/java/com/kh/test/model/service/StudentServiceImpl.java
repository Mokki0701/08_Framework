package com.kh.test.model.service;

import org.springframework.stereotype.Service;

import com.kh.test.model.dto.Student;
import com.kh.test.model.mapper.StudentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private StudentMapper mapper;
	
	@Override
	public int addStrudnet(Student student) {
	
		return mapper.addStudent(student);
	}
	
}
