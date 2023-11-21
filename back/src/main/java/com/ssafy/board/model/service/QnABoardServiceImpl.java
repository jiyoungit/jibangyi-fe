package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.QnABoardDto;
import com.ssafy.board.model.QnABoardListDto;
import com.ssafy.board.model.QnABoardParameterDto;
import com.ssafy.board.model.mapper.QnABoardMapper;

@Service
public class QnABoardServiceImpl implements QnABoardService {
	
	QnABoardMapper mapper;
	
	public QnABoardServiceImpl(QnABoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void writeArticle(QnABoardDto qnaBoardDto) throws Exception {
		mapper.writeArticle(qnaBoardDto);
	}

	@Override
	public QnABoardListDto listArticle(QnABoardParameterDto param) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int pgno = param.getPgno();
		int spp = param.getSpp();
		int start = (pgno - 1) * spp;
		int listsize = spp;
		map.put("start", start);
		map.put("listsize", listsize);
		map.put("key", param.getKey());
		map.put("word", param.getWord());
		
		List<QnABoardDto> list = mapper.listArticle(map);
		int totalSize = mapper.getTotalArticleCount(map);
		
		QnABoardListDto qnABoardListDto = new QnABoardListDto();
		qnABoardListDto.setArticles(list);
		qnABoardListDto.setCurrentPage(pgno);
		qnABoardListDto.setTotalPageCount(totalSize);
		
		return qnABoardListDto;
	}

	@Override
	public QnABoardDto getArticle(int qnaNo) throws Exception {
		return mapper.getArticle(qnaNo);
	}

	@Override
	public void updateHit(int qnaNo) throws Exception {
		mapper.updateHit(qnaNo);
	}

	@Override
	public void modifyArticle(QnABoardDto qnaBoardDto) throws Exception {
		mapper.modifyArticle(qnaBoardDto);
	}

	@Override
	public void deleteArticle(int qnaNo) throws Exception {
		mapper.deleteArticle(qnaNo);
	}

}
