package com.ssafy.board.model;

import java.util.List;

import lombok.Data;

@Data
public class QnABoardListDto {

	private List<QnABoardDto> articles;
	private int currentPage;
	private int totalPageCount;

}
