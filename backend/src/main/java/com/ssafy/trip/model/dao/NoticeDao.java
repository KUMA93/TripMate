package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.dto.NoticeDto;
import com.ssafy.trip.model.dto.PageBean;

@Repository
public interface NoticeDao {
	NoticeDto search(int articleNo) throws SQLException;
	List<NoticeDto> searchAll(PageBean bean) throws SQLException;
	int totalCount(PageBean bean);
	void updateHit(int articleNo) throws SQLException;
	void insert(NoticeDto noticeDto) throws SQLException;
	void update(NoticeDto noticeDto) throws SQLException;
	void delete(int articleNo) throws SQLException;
}
