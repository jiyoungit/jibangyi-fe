package com.ssafy.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.QnABoardDto;

@Mapper
public interface QnABoardMapper {

	void writeArticle(QnABoardDto qnaBoardDto) throws Exception;
	List<QnABoardDto> listArticle(Map<String, Object> map) throws Exception;
	QnABoardDto getArticle(int qnaNo) throws Exception;
	void updateHit(int qnaNo) throws Exception;
	void modifyArticle(QnABoardDto qnaBoardDto) throws Exception;
	void deleteArticle(int qnaNo) throws Exception;
	int getTotalArticleCount(Map<String, Object> map) throws Exception;
	
}
