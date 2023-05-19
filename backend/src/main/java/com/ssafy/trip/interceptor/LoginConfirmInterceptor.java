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
public class LoginConfirmInterceptor implements HandlerInterceptor { 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		
		if(user == null) {
			throw new TripException("로그인이 필요한 서비스입니다.");
//			response.sendRedirect(request.getContextPath());
		}
		
		return true;
	}
	
}