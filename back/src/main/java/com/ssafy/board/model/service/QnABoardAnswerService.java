package com.ssafy.board.model.service;

import com.ssafy.board.model.QnABoardAnswerDto;

public interface QnABoardAnswerService {
	void writeAnswer(QnABoardAnswerDto anABoardAnswerDto) throws Exception;
	QnABoardAnswerDto getAnswer(int qnaNo) throws Exception;
	void modifyAnswer(QnABoardAnswerDto qnaBoardDto) throws Exception;
	void deleteAnswer(int answerNo) throws Exception;
}
