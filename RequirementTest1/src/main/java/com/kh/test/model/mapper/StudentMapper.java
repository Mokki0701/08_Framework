package com.kh.test.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.test.model.dto.Student;

@Mapper
public interface StudentMapper {

	int addStudent(Student student);


}
