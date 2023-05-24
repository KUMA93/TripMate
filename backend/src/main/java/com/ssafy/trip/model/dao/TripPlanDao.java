package com.ssafy.trip.model.dao;

import java.util.List;

import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.dto.TripPlanDto;

public interface TripPlanDao {
	List<TripPlanDto> searchAll(PageBean bean);
	TripPlanDto search(int articleNo);
	void updateHit(int articleNo);
	void insert(TripPlanDto tripPlanDto);
	void update (TripPlanDto tripPlanDto);
	void delete (int articleNo);
	int totalCount(PageBean bean);

}
