package com.kh.test.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String userId;
	private String boardContent;
	private int boardReadcount;
	private String boardDate;


}
