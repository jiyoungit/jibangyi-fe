package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MemberDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class QnABoardParameterDto {
	@ApiModelProperty(value = "현재 페이지 번호", required = true)
	private int pgno;
	@ApiModelProperty(value = "페이지 하나 당 표시할 게시글 개수", required = true)
	private int spp;
	@ApiModelProperty(value = "검색 카테고리")
	private String key;
	@ApiModelProperty(value = "검색 키워드")
	private String word;
}
