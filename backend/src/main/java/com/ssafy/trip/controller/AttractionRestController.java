package com.ssafy.trip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.AttractionInfo;
import com.ssafy.trip.model.service.AttractionService;
import com.ssafy.trip.model.service.AttractionServiceImpl;

@RestController
public class AttractionRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AttractionRestController.class);

	@Autowired
	private AttractionService service;

	@RequestMapping(value = "/rest/trip/search", method = RequestMethod.GET)
	private ResponseEntity<List<AttractionInfo>> returnJson(
			@RequestParam(value = "lat", required = false, defaultValue = "37.500613") Double lat,
			@RequestParam(value = "lon", required = false, defaultValue = "127.036431") Double lon,
			@RequestParam(value = "sido", required = false, defaultValue = "0") int sidoCode,
			@RequestParam(value = "gugun", required = false, defaultValue = "0") int gugunCode,
			@RequestParam(value = "contentType", required = false, defaultValue = "0") int contentType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
			
		) {

		AttractionInfo searchInfo = new AttractionInfo();

//		logger.debug("trip search................................paramMap : {}", paramMap);

		String input;
		searchInfo.setLatitude(lat);
		searchInfo.setLongitude(lon);
		
		if (sidoCode != 0) {
			searchInfo.setSido_code(sidoCode);
		}
		if (gugunCode != 0) {
			searchInfo.setGugun_code(gugunCode);
		}
		if (contentType != 0) {
			searchInfo.setContentTypeId(contentType);
		}
		if (keyword != null && !keyword.equals("")) {
			searchInfo.setTitle(keyword);
		}

		logger.debug("trip search................................searchInfo : {}", searchInfo);

		return new ResponseEntity<List<AttractionInfo>>(service.getAllAttractions(searchInfo), HttpStatus.OK);

	}

}
