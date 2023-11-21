package com.ssafy.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.QnABoardAnswerDto;
import com.ssafy.board.model.QnABoardDto;
import com.ssafy.board.model.QnABoardListDto;
import com.ssafy.board.model.QnABoardParameterDto;
import com.ssafy.board.model.service.QnABoardAnswerService;
import com.ssafy.board.model.service.QnABoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/qna")
@Api("Q&A 컨트롤러  API")
public class QnABoardController {
	QnABoardService qnABoardService;
	QnABoardAnswerService qnABoardAnswerService;

	public QnABoardController(QnABoardService qnABoardService, QnABoardAnswerService qnABoardAnswerService) {
		this.qnABoardService = qnABoardService;
		this.qnABoardAnswerService = qnABoardAnswerService;
	}

	@ApiOperation(value = "QnA 글작성", notes = "새로운 QnA 정보를 입력한다.")
	@PostMapping
	public ResponseEntity<?> writeArticle(
			@RequestBody @ApiParam(value = "Q&A 게시글 정보.", required = true) QnABoardDto qnABoardDto) {
		log.info("writeArticle qnABoardDto - {}", qnABoardDto);
		try {
			qnABoardService.writeArticle(qnABoardDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "QnA 글목록", notes = "모든 QnA 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<?> listArticle(
			@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) QnABoardParameterDto param) {
		log.info("listArticle param - {}", param);
		try {
			QnABoardListDto qnaBoardListDto = qnABoardService.listArticle(param);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(qnaBoardListDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "QnA 글보기", notes = "글번호에 해당하는 QnA 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{qnano}")
	public ResponseEntity<QnABoardDto> getArticle(
			@PathVariable("qnano") @ApiParam(value = "얻어올 글의 글번호.", required = true) int qnano)
			throws Exception {
		log.info("getArticle - 호출 : " + qnano);
		qnABoardService.updateHit(qnano);
		return new ResponseEntity<QnABoardDto>(qnABoardService.getArticle(qnano), HttpStatus.OK);
	}

	@ApiOperation(value = "QnA 글수정", notes = "수정할 QnA 게시글 정보를 입력한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QnABoardDto qnABoardDto) throws Exception {
		log.info("modifyArticle - 호출 {}", qnABoardDto);

		qnABoardService.modifyArticle(qnABoardDto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "QnA 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/{qnano}")
	public ResponseEntity<String> deleteArticle(@PathVariable("qnano") @ApiParam(value = "삭제할 글의 글번호.", required = true) int qnano) throws Exception {
		log.info("deleteArticle - 호출");
		qnABoardService.deleteArticle(qnano);
		return ResponseEntity.ok().build();
	}
	
	// QnA 답글
	
	@ApiOperation(value = "QnA 답글 작성", notes = "작성된 QnA 게시글에 답글을 입력한다.")
	@PostMapping("/answer")
	public ResponseEntity<?> writeAnswer(
			@RequestBody @ApiParam(value = "답글 정보.", required = true) QnABoardAnswerDto qnABoardAnswerDto) {
		log.info("writeAnswer qnABoardAnswerDto - {}", qnABoardAnswerDto);
		try {
			qnABoardAnswerService.writeAnswer(qnABoardAnswerDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "QnA 답글보기", notes = "Q&A 게시글에 해당하는 답글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/answer/{qnano}")
	public ResponseEntity<QnABoardAnswerDto> getAnswer(
			@PathVariable("qnano") @ApiParam(value = "얻어올 글의 글번호.", required = true) int qnano)
			throws Exception {
		log.info("getAnswer - 호출 : " + qnano);
		return new ResponseEntity<QnABoardAnswerDto>(qnABoardAnswerService.getAnswer(qnano), HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA 답글수정", notes = "수정할 QnA 답글 정보를 입력한다.", response = String.class)
	@PutMapping("/answer")
	public ResponseEntity<String> modifyAnswer(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QnABoardAnswerDto qnABoardAnswerDto) throws Exception {
		log.info("modifyArticle - 호출 {}", qnABoardAnswerDto);

		qnABoardAnswerService.modifyAnswer(qnABoardAnswerDto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "QnA 답글삭제", notes = "글번호에 해당하는 답글의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/answer/{answerno}")
	public ResponseEntity<String> deleteAnswer(@PathVariable("answerno") @ApiParam(value = "삭제할 글의 글번호.", required = true) int answerno) throws Exception {
		log.info("deleteArticle - 호출");
		qnABoardAnswerService.deleteAnswer(answerno);
		return ResponseEntity.ok().build();
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
