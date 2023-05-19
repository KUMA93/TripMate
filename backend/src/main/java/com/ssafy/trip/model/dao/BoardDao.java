package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.model.dto.BoardDto;
import com.ssafy.trip.model.dto.PageBean;

public interface BoardDao {
	void insert(BoardDto boardDto) throws SQLException;
	void update(BoardDto boardDto) throws SQLException;
	void delete(int articleNo) throws SQLException;
	BoardDto search(int articleNo) throws SQLException;
	List<BoardDto> searchAll(PageBean bean) throws SQLException;
	int totalCount(PageBean bean);
	void updateHit(int articleNo) throws SQLException;
	
}
