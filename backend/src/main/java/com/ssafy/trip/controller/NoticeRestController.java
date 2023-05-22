package com.ssafy.trip.controller;

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

import com.ssafy.trip.model.dto.NoticeDto;
import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.service.NoticeService;


@RestController
@RequestMapping("/rest/notice")
@CrossOrigin("*")
public class NoticeRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeRestController.class);
	private static final String SUCCESS = "success";
	
	@Autowired
	private NoticeService noticeService;

	public NoticeRestController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	
	@GetMapping
	public ResponseEntity<?> list(PageBean bean){
		List<NoticeDto> notices = noticeService.searchAll(bean);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("notices", notices);
		result.put("page", bean);
		if (notices.isEmpty() || notices == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> detail(@PathVariable("articleNo") int articleNo){
		NoticeDto notice = noticeService.search(articleNo);
		noticeService.updateHit(articleNo);
		
		if (notice == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<NoticeDto> (notice, HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody NoticeDto article) {
		logger.debug("=============공지 등록 ============={}", article);
		noticeService.insert(article);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody NoticeDto article) {
		noticeService.update(article);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> delete(int articleNo) {
		noticeService.delete(articleNo);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
