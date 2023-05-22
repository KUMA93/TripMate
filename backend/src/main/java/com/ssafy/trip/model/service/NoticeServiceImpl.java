package com.ssafy.trip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.NoticeDao;
import com.ssafy.trip.model.dto.NoticeDto;
import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.util.PageUtility;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	NoticeDao dao;
	
	public NoticeServiceImpl(NoticeDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public NoticeDto search(int articleNo) {
		
		try {
			NoticeDto noticeDto = dao.search(articleNo);
			
			if(noticeDto == null) {
				throw new TripException("요청한 공지사항은 삭제되었습니다.");
			}
			
			return noticeDto;
		} catch (Exception e) {
			throw new TripException("공지 조회 중 오류 발생");
		}
	}

	@Override
	public List<NoticeDto> searchAll(PageBean bean) {
		
		try {
			int total = dao.totalCount(bean);
			bean.setTotal(total);
			
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar());
			
			return dao.searchAll(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TripException("공지사항 목록을 불러오는 중 오류 발생");
		}
		 
	}

	@Override
	public void updateHit(int articleNo) {
		try {
			dao.updateHit(articleNo);
		} catch (Exception e) {
			throw new TripException("공지사항 조회수 증가 중 오류 발생");
		}
		
	}

	@Override
	public void insert(NoticeDto noticeDto) {
		try {
			dao.insert(noticeDto);
		} catch (Exception e) {
			throw new TripException("공지사항 등록 중 오류 발생");
		}
	}

	@Override
	public void update(NoticeDto noticeDto) {
		try {
			dao.update(noticeDto);
		} catch (Exception e) {
			throw new TripException("공지사항 수정 중 오류 발생");
		}
	}

	@Override
	public void delete(int articleNo) {
		try {
			dao.delete(articleNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TripException("공지사항 삭제 중 오류 발생");
		}
	}

}
