package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.dto.AttractionInfo;

@Repository
public interface AttractionDao {

	List<AttractionInfo> getAllAttractionInfos(AttractionInfo searchInfo) throws SQLException;

	AttractionInfo getAttractionInfo(int contentId) throws SQLException;
}
