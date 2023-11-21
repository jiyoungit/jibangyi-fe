package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "QnABoardAnswerDto : Q&A 답글 정보", description = "Q&A 게시글 답글 정보를 나타낸다.")
@Data
public class QnABoardAnswerDto {
	@ApiModelProperty(value = "답글 번호")
	private int answerNo;
	@ApiModelProperty(value = "질문글 번호", example = "0")
	private int qnaNo;
	@ApiModelProperty(value = "답글 작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "답글 작성자 이름")
	private String userName;
	@ApiModelProperty(value = "답글 제목")
	private String subject;
	@ApiModelProperty(value = "답글 내용")
	private String content;
	@ApiModelProperty(value = "답글 작성 시기")
	private String registerTime;
}
