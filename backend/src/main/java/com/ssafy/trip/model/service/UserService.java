package com.ssafy.trip.model.service;


import java.util.List;

import com.ssafy.trip.model.dto.User;

public interface UserService {

	public User login(User user) throws Exception;
	public User userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	public void regist(User user) ;
	public void update(User user) ;
	public void remove(String user_id) ;
	public User search(String id) ;
}
