package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.dto.BoardDto;

public interface BoardService {
	void insert(BoardDto boardDto);
	void update(BoardDto boardDto);
	void updateHit(int articleNo);
	void delete(int articleNo);
	BoardDto search(int articleNo);
	List<BoardDto> searchAll(PageBean bean);
}
