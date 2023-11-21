package com.ssafy.board.model.service;

import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.QnABoardDto;
import com.ssafy.board.model.QnABoardListDto;
import com.ssafy.board.model.QnABoardParameterDto;

public interface QnABoardService {

	void writeArticle(QnABoardDto qnaBoardDto) throws Exception;
	QnABoardListDto listArticle(QnABoardParameterDto param) throws Exception;
	QnABoardDto getArticle(int qnaNo) throws Exception;
	void updateHit(int qnaNo) throws Exception;
	void modifyArticle(QnABoardDto qnaBoardDto) throws Exception;
	void deleteArticle(int qnaNo) throws Exception;
	
}
