package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.dto.User;

@Repository
public interface UserDao {

	public User login(User user) throws SQLException;
	public User userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public User search(String id) throws SQLException;
	public void regist(User user) throws SQLException;
	public void update(User user) throws SQLException;
	public void remove(String id) throws SQLException;
}
