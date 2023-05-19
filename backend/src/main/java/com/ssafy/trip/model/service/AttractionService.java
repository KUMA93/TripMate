package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.model.dto.AttractionInfo;

public interface AttractionService {
	public List<AttractionInfo> getAllAttractions(AttractionInfo searchInfo);
}
