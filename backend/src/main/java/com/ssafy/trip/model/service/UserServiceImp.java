package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.UserDao;
import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.util.PasswordUtil;

@Service
public class UserServiceImp implements UserService {
	

	@Autowired
	private SqlSession sqlSession;

	@Override
	public User login(User user) throws Exception {
		try {
			User originUser = sqlSession.getMapper(UserDao.class).search(user.getId());
			if (user == null) 
				throw new TripException("등록되지 않은 아이디입니다.");
			
			if (!PasswordUtil.verifyPassword(user.getPass(), originUser.getPass()))
				throw new TripException("비밀번호가 틀렸습니다.");
			
			return user;
		} catch (SQLException e) {
			throw new TripException("로그인 처리 중 오류 발생");
		}

	}

	@Override
	public User userInfo(String id) throws Exception {
		return sqlSession.getMapper(UserDao.class).userInfo(id);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserDao.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return sqlSession.getMapper(UserDao.class).getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		sqlSession.getMapper(UserDao.class).deleteRefreshToken(map);
	}
	

	@Override
	public void regist(User user) {
		try {
			User find = sqlSession.getMapper(UserDao.class).search(user.getId());
			
			if (find != null) {
				throw new TripException("이미 등록된 아이디입니다.");
			}
			
			sqlSession.getMapper(UserDao.class).regist(user);
			
			System.out.println("dao regist.............................");
			
		} catch (SQLException e) {
			throw new TripException("회원정보 등록 중 오류 발생");
		}
	}

	@Override
	public void update(User user) {
		try {
			sqlSession.getMapper(UserDao.class).update(user);
			
		}catch (SQLException e) {
			throw new TripException("회원정보 수정 중 오류 발생");
		}
	}

	@Override
	public void remove(String id) {
		try {
			sqlSession.getMapper(UserDao.class).remove(id);
		} catch (SQLException e) {
			throw new TripException("회원정보 삭제 중 오류 발생");
		}
	}
	
	@Override
	public User search(String id) {
		try {
			User user = sqlSession.getMapper(UserDao.class).search(id);;
			return user;
			
		} catch (SQLException e) {
			throw new TripException("회원정보 검색 중 오류 발생");
		}
	}


}
