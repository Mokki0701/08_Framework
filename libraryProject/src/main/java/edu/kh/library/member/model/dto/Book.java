package edu.kh.library.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // Spring EL, Mybatis
@Setter // 커맨드 객체
@NoArgsConstructor 
@ToString
@Builder
@AllArgsConstructor
public class Book {
	
	private int bookNo;
	private String bookTitle;
	private String bookWriter;
	private int bookPrice;
	private String regDate;

}
