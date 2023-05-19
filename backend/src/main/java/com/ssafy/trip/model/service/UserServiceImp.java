package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.UserDao;
import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.util.PasswordUtil;

@Service
public class UserServiceImp implements UserService {
	
	private UserDao dao;
	
	private UserServiceImp(UserDao dao) {
		this.dao = dao;
	}
	
	
	@Override
	public User login(String user_id, String pass) {
		try {
			User user = dao.search(user_id);
			System.out.println(user);
			if (user == null) 
				throw new TripException("등록되지 않은 아이디입니다.");
			
			if (!PasswordUtil.verifyPassword(pass, user.getPass()))
				throw new TripException("비밀번호가 틀렸습니다.");
			
			return user;
		} catch (SQLException e) {
			throw new TripException("로그인 처리 중 오류 발생");
		}
	}
	
	
	@Override
	public List<User> searchAll() {
		try {
			List<User> users = dao.searchAll();
			return users;
		} catch (SQLException e) {
			throw new TripException("회원정보 검색 중 오류 발생");
		}
	}


	@Override
	public User search(String user_id) {
		try {
			User user = dao.search(user_id);
			return user;
			
		} catch (SQLException e) {
			throw new TripException("회원정보 검색 중 오류 발생");
		}
	}

	@Override
	public void regist(User user) {
		try {
			User find = dao.search(user.getId());
			
			if (find != null) {
				throw new TripException("이미 등록된 아이디입니다.");
			}
			
			dao.regist(user);
			
			System.out.println("dao regist.............................");
			
		} catch (SQLException e) {
			throw new TripException("회원정보 등록 중 오류 발생");
		}
	}

	@Override
	public void update(User user) {
		try {
			dao.update(user);
			
		}catch (SQLException e) {
			throw new TripException("회원정보 수정 중 오류 발생");
		}
	}

	@Override
	public void remove(String user_id) {
		try {
			dao.remove(user_id);
		} catch (SQLException e) {
			throw new TripException("회원정보 삭제 중 오류 발생");
		}
	}

}
