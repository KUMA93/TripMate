package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.model.dto.User;


public interface UserDao {
	/**
	 * 입력된 id/pass 기반으로 User를 조회해서 반환한다.
	 * @param user_id
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	List<User> searchAll() throws SQLException;
	User search(String id) throws SQLException;
	void regist(User user) throws SQLException;
	void update(User user) throws SQLException;
	void remove(String id) throws SQLException;
}
