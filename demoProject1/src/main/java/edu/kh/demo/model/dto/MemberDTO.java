package edu.kh.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Lombok 라이브러리

// Lombok : 자주 사용하는 코드를 컴파일 시 자동 완성 해주는 라이브러리
// -> DTO(기본생성자, getter/setter, toSTring), log

@NoArgsConstructor // 기본 생성자
@Getter
@Setter
@ToString
public class MemberDTO {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
	
	
	
	
}
