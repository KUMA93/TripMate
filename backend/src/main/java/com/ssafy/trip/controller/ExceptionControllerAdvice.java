package com.ssafy.trip.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	/* @ExceptionHandler(처리할 Exception 종류) */
	@ExceptionHandler(Exception.class)
	public String handler(Exception e, Model model) {
		e.printStackTrace();
		logger.error("오류 발생 >>>>>>>>>>>>>>>>> : {}", e.getMessage());
		model.addAttribute("msg", e.getMessage());
		return "/error/error"; // forward로 이동
	}
	
	/*
	 * 404 error : 요청 페이지가 없는 경우 발생하는 오류
	 * 404 오류가 발생하는 WAS가 처리하지 않고 Exception을 발생시키도록 web.xml에 설정해야 한다.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handler(NoHandlerFoundException e, Model model) {
		e.printStackTrace();
		logger.error("오류 발생 >>>>>>>>>>>>>>>>> : {}", "요청한 페이지를 찾을 수 없습니다.");
		model.addAttribute("msg", "요청한 페이지를 찾을 수 없습니다.");
		return "/error/error"; // forward로 이동
	}
	
	
	
}
