package com.ssafy.trip.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.dto.HotplaceDto;
import com.ssafy.trip.model.dto.PageBean;

@Repository
public interface HotplaceDao {
	List<HotplaceDto> searchAll(PageBean bean);
	HotplaceDto search(int articleNo);
	void updateHit(int articleNo);
	void updateLike(int articleNo);
	void cancelLike(int articleNo);
	void insert(HotplaceDto hotplaceDto);
	void update(HotplaceDto hotplaceDto);
	void delete(int articleNo);
	int totalCount(PageBean bean);
}
