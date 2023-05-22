package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.AttractionDao;
import com.ssafy.trip.model.dto.AttractionInfo;
import com.ssafy.trip.model.dto.TripException;

@Service
public class AttractionServiceImpl implements AttractionService {
	
	private AttractionDao dao;
	
	private AttractionServiceImpl(AttractionDao dao) {
		this.dao = dao;
	}

	@Override
	public List<AttractionInfo> getAllAttractions(AttractionInfo searchInfo) {
		try {
			System.out.println(searchInfo.toString());
			return dao.getAllAttractionInfos(searchInfo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripException(e.getMessage());
		}
	}

	@Override
	public AttractionInfo getAttraction(int contentId) {
		try {
			return dao.getAttractionInfo(contentId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripException(e.getMessage());
		}
	}

}
