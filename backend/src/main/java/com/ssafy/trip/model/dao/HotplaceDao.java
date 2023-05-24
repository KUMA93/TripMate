package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.dto.HotplaceDto;
import com.ssafy.trip.model.dto.LikeDto;
import com.ssafy.trip.model.dto.PageBean;

@Repository
public interface HotplaceDao {
	List<HotplaceDto> searchAll(PageBean bean);
	HotplaceDto search(int articleNo);
	void updateHit(int articleNo);
	int isLike(LikeDto likeDto);
	void updateLike(LikeDto likeDto);
	void insertLike(LikeDto likeDto);
	void cancelLike(LikeDto likeDto);
	void deleteLike(LikeDto likeDto);
	int getLike(int articleNo);
	void insert(HotplaceDto hotplaceDto);
	void update(HotplaceDto hotplaceDto);
	void delete(int articleNo);
	int totalCount(PageBean bean);
}
