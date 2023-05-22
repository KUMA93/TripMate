package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.model.dao.HotplaceDao;
import com.ssafy.trip.model.dto.HotplaceDto;
import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.util.PageUtility;

@Service
public class HotplaceServiceImpl implements HotplaceService{
	HotplaceDao dao;
	
	public HotplaceServiceImpl(HotplaceDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void insert(HotplaceDto hotplaceDto) {
		try {	
			dao.insert(hotplaceDto);
		} catch (Exception e) {
			throw new TripException("게시글 등록 중 오류 발생");
		}
	}
	
	@Transactional
	@Override
	public void update(HotplaceDto hotplaceDto) {
		try {
			dao.update(hotplaceDto);
		} catch (Exception e) {
			throw new TripException("게시글 정보 수정 중 오류 발생");
		}
	}
	
	@Override
	public void updateHit(int articleNo) {
		try {
			dao.updateHit(articleNo);
		} catch (Exception e) {
			throw new TripException("게시글 조회수 증가 중 오류 발생");
		}
	}
	
	@Override
	public void updateLike(int articleNo) {
		try {
			dao.updateLike(articleNo);
		} catch (Exception e) {
			throw new TripException("게시글 좋아요 증가 중 오류 발생");
		}
	}
	
	@Transactional
	@Override
	public void delete(int articleNo) {
		try {
			dao.delete(articleNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TripException("게시글 삭제 중 오류 발생");
		}
	}

	@Override
	public HotplaceDto search(int articleNo) {
		try {
			HotplaceDto hotplaceDto = dao.search(articleNo);
			
			if(hotplaceDto == null) {
				throw new TripException("요청한 게시글은 삭제되었습니다.");
			}
			return hotplaceDto;
		} catch (Exception e) {
			throw new TripException("게시글 정보 조회 중 오류 발생");
		}
	}

	@Override
	public List<HotplaceDto> searchAll(PageBean bean) {
		try {
			List<HotplaceDto> first = dao.searchAll(bean);
			int total = dao.totalCount(bean);
			bean.setTotal(total);
			
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar());
			
			return first;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TripException("게시글 목록 정보를 조회 하는 중 오류 발생!!!");
		}
	}
}
