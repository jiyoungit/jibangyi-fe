package com.ssafy.board.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "QnABoardDto : 게시글정보", description = "Q&A 게시글 상세 정보를 나타낸다.")
@Data
public class QnABoardDto {

	@ApiModelProperty(value = "글번호")
	private int qnaNo;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "작성자 이름")
	private String userName;
	@ApiModelProperty(value = "글제목")
	private String subject;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "작성일")	
	private String registerTime;

}
