package com.ssafy.board.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.QnABoardAnswerDto;
import com.ssafy.board.model.mapper.QnABoardAnswerMapper;

@Service
public class QnABoardAnswerServiceImpl implements QnABoardAnswerService {
	
	QnABoardAnswerMapper mapper;

	public QnABoardAnswerServiceImpl(QnABoardAnswerMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void writeAnswer(QnABoardAnswerDto qnABoardAnswerDto) throws Exception {
		mapper.writeAnswer(qnABoardAnswerDto);
	}

	@Override
	public QnABoardAnswerDto getAnswer(int qnaNo) throws Exception {
		return mapper.getAnswer(qnaNo);
	}

	@Override
	public void modifyAnswer(QnABoardAnswerDto qnABoardAnswerDto) throws Exception {
		mapper.modifyAnswer(qnABoardAnswerDto);
	}

	@Override
	public void deleteAnswer(int answerNo) throws Exception {
		mapper.deleteAnswer(answerNo);
	}

}
