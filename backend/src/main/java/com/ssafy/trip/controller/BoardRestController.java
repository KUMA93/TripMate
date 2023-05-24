package com.ssafy.trip.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.BoardDto;
import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.service.BoardService;

@RestController
@RequestMapping("/rest/board")
@CrossOrigin("*")
public class BoardRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private static final String SUCCESS = "success";
	
	@Autowired
	private BoardService boardService;

	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping
	public ResponseEntity<?> list(PageBean bean) {
		logger.debug("board..............................list");
		List<BoardDto> articles = boardService.searchAll(bean);
		
		LocalDateTime now = LocalDateTime.now();
		for(BoardDto article : articles) {
			LocalDateTime d = LocalDateTime.parse(article.getRegisterTime(), 
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	
			if (d.getYear()==now.getYear() && d.getMonth()==now.getMonth() && d.getDayOfMonth()==now.getDayOfMonth()) {
				article.setRegisterTime(d.format(DateTimeFormatter.ofPattern("HH:MM")));
			}else {
				article.setRegisterTime(d.format(DateTimeFormatter.ofPattern("YY.MM.dd")));
			}
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("articles", articles);
		result.put("page", bean);
		if (articles.isEmpty() || articles == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> detail(@PathVariable("articleNo") int articleNo) {
		logger.debug("board..............................detail : {}", articleNo);
		
		BoardDto article = boardService.search(articleNo);
		boardService.updateHit(articleNo);
		
		if (article == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<BoardDto> (article, HttpStatus.OK);
		}
	}

	
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody BoardDto article) {
		logger.debug("board..............................regist : {}", article);
		boardService.insert(article);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody BoardDto article) {
		logger.debug("board..............................update : {}", article);
		boardService.update(article);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(int articleNo) {
		logger.debug("board..............................delete : {}", articleNo);
		boardService.delete(articleNo);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
}
