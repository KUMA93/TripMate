package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trip.model.dto.BoardDto;
import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/list")
	public String list(Model model, PageBean bean) {
		
		List<BoardDto> articles = boardService.searchAll(bean);
		
		model.addAttribute("articles", articles);
		
		return "/board/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("articleNo") int articleNo, Model model) {
		
		BoardDto article = boardService.search(articleNo);
		boardService.updateHit(articleNo);
		model.addAttribute("article", article);
		
		return "/board/detail";
	}
	
	@GetMapping("/regist")
	public void regist() {
		
	}
	
	@PostMapping("/regist")
	public String regist(BoardDto article) {
		
		boardService.insert(article);
		
		return "redirect:/board/list";
	}

	@GetMapping("/update")
	public String update(@RequestParam("articleNo") int articleNo, Model model) {
		
		BoardDto article = boardService.search(articleNo);
		
		model.addAttribute("article", article);
		
		return "/board/update";
	}
	
	@PostMapping("/update")
	public String update(BoardDto article) {
		
		boardService.update(article);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String delete(int articleNo) {
		
		boardService.delete(articleNo);
		
		return "redirect:/board/list";
	}
	
}
