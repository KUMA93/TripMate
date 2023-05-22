package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.model.dto.NoticeDto;
import com.ssafy.trip.model.dto.PageBean;

public interface NoticeService {
	public NoticeDto search(int articleNo);
	public List<NoticeDto> searchAll(PageBean bean);
	public void updateHit(int articleNo);
	public void insert(NoticeDto noticeDto);
	public void update(NoticeDto noticeDto);
	public void delete(int articleNo);
}
