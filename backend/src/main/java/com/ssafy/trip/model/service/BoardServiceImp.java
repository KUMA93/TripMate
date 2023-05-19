package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.model.dto.PageBean;
import com.ssafy.trip.model.dao.BoardDao;
import com.ssafy.trip.model.dto.BoardDto;
import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.util.PageUtility;

@Service
public class BoardServiceImp implements BoardService {
	
	BoardDao dao;
	
	public BoardServiceImp(BoardDao dao) {
		super();
		this.dao = dao;
	}

	@Transactional
	public void insert(BoardDto boardDto) {
		try {	
			dao.insert(boardDto);
		} catch (SQLException e) {
			throw new TripException("게시글 등록 중 오류 발생");
		}
	}
	
	@Transactional
	@Override
	public void update(BoardDto boardDto) {
		try {
			dao.update(boardDto);
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
	public BoardDto search(int articleNo) {
		try {
			BoardDto boardDto = dao.search(articleNo);
			
			if(boardDto == null) {
				throw new TripException("요청한 게시글은 삭제되었습니다.");
			}
			return boardDto;
		} catch (SQLException e) {
			throw new TripException("게시글 정보 조회 중 오류 발생");
		}
	}

	@Override
	public List<BoardDto> searchAll(PageBean bean) {
		try {
			int total = dao.totalCount(bean);
			bean.setTotal(total);
			
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar());
			
			return dao.searchAll(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TripException("게시글 목록 정보를 조회 하는 중 오류 발생!!!");
		}
	}
}
