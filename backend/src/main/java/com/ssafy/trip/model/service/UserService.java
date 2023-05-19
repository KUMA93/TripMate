package com.ssafy.trip.model.service;


import java.util.List;

import com.ssafy.trip.model.dto.User;

public interface UserService {
	User login(String user_id, String pass) ;
	User search(String user_id) ;
	void regist(User user) ;
	void update(User user) ;
	void remove(String user_id) ;
	List<User> searchAll();
}
