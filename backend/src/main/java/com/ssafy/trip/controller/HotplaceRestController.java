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

import com.ssafy.trip.model.dto.HotplaceDto;
import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.service.HotplaceService;

@RestController
@RequestMapping("/rest/hotplace")
@CrossOrigin("*")
public class HotplaceRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotplaceRestController.class);
	private static final String SUCCESS = "success";
	
	@Autowired
	private HotplaceService hotplaceService;

	public HotplaceRestController(HotplaceService hotplaceService) {
		this.hotplaceService = hotplaceService;
	}
	
	@GetMapping
	public ResponseEntity<?> list(PageBean bean) {
		List<HotplaceDto> articles = hotplaceService.searchAll(bean);
		
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
		
		HotplaceDto article = hotplaceService.search(articleNo);
		hotplaceService.updateHit(articleNo);
		
		if (article == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<HotplaceDto> (article, HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody HotplaceDto article) {
		logger.debug("board..............................regist : {}", article);
		hotplaceService.insert(article);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@PutMapping("/like/{articleNo}")
	public ResponseEntity<String> updateLike(@PathVariable("articleNo") int articleNo) {
		hotplaceService.updateLike(articleNo);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody HotplaceDto article) {
		logger.debug("board..............................update : {}", article);
		hotplaceService.update(article);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(int articleNo) {
		logger.debug("board..............................delete : {}", articleNo);
		hotplaceService.delete(articleNo);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
