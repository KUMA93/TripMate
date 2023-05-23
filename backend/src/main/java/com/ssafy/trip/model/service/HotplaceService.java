package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.model.dto.HotplaceDto;
import com.ssafy.trip.model.dto.LikeDto;
import com.ssafy.trip.model.dto.PageBean;

public interface HotplaceService {
	public List<HotplaceDto> searchAll(PageBean bean);
	public HotplaceDto search(int articleNo);
	public void updateHit(int articleNo);
	public int isLike(LikeDto likeDto);
	public void updateLike(LikeDto likeDto);
	public void insertLike(LikeDto likeDto);
	public void cancelLike(LikeDto likeDto);
	public void deleteLike(LikeDto likeDto);
	public void insert(HotplaceDto hotplaceDto);
	public void update(HotplaceDto hotplaceDto);
	public void delete(int articleNo);
}
