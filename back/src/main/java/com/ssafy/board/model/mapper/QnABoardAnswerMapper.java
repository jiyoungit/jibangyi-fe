package com.ssafy.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.QnABoardAnswerDto;

@Mapper
public interface QnABoardAnswerMapper {
	void writeAnswer(QnABoardAnswerDto qnABoardAnswerDto) throws Exception;
	QnABoardAnswerDto getAnswer(int qnaNo) throws Exception;
	void modifyAnswer(QnABoardAnswerDto qnABoardAnswerDto) throws Exception;
	void deleteAnswer(int answerNo) throws Exception;
}
