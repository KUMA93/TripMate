package com.ssafy.trip.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.model.dto.User;



//@SuppressWarnings("deprecation")
//public class ConfirmInterceptor extends HandlerInterceptorAdapter {
//spring 5.3 부터는 HandlerInterceptor implements
public class AdminConfirmInterceptor implements HandlerInterceptor { 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		
		if(user == null || !user.getId().equals("admin") ) {
			throw new TripException("관리자 적용 페이지입니다. 접근 권한이 없습니다.");
//			response.sendRedirect(request.getContextPath());
		}
		
		return true;
	}
	
}