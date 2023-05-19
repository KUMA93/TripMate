package com.ssafy.trip.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.model.service.UserService;
import com.ssafy.trip.model.service.UserServiceImp;
import com.ssafy.trip.util.PasswordUtil;

@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@PostMapping("/updatePW")
	private String updatePW(@ModelAttribute("id") String id, String pw) {
		User user = service.search(id);
		
		user.setPass(PasswordUtil.encryptPassword(pw));
		service.update(user);
		
		return "redirect:/index";
	}
		
	@GetMapping("/findPW")
	private String findPW() {
		return "/user/findPW";
	}
	
	@PostMapping("/findPW")
	private String findPw(String id, String email, Model model) {
		User user = service.search(id);
		
		
		if (user == null) {
			model.addAttribute("msg", "등록되지 않은 id입니다.");
			return "/user/findPW";
		}
		
		if (!email.equals(user.getEmail())) {
			model.addAttribute("msg", "이메일이 일치하지 않습니다.");
			return "/user/findPW";
		}
		
		model.addAttribute("id", id);
		return "/user/updatePW";
	}
	
	@GetMapping("remove")
	private String remove(HttpSession session) {
		String id = getUser(session).getId();
		service.remove(id);
		session.invalidate();
		
		return "/index";
	}
	
	@PostMapping("/update")
	private String update(String user_name, String user_email, String nextPw, HttpSession session){
		User user = getUser(session);
		
		User next = new User(user.getId(), PasswordUtil.encryptPassword(nextPw), 
				user_name, user_email, user.getPosition(), user.getSido_code());
		log.info("================ next: {} ==============", next.getPass());
		service.update(next);
		session.invalidate();
		
		return "/user/mypage";
	}

	private User getUser(HttpSession session) {
		User user = (User) session.getAttribute("userInfo");
		
		return user;
	}
	
	@GetMapping("/mypage")
	private String mypage(HttpSession session, Model model) {
		
		User user = getUser(session);
		if (user == null) {
			model.addAttribute("msg", "로그인 후 이용 가능합니다.");
			model.addAttribute("referer", "/user/mypage");
			return "/index";
		} else {
			return "/user/mypage";
		}
	}
	
	@PostMapping("/regist")
	private String regist(String id, String pw, String name, String email, HttpSession session){
		String position = "user";
		int sido_code = 12;
		
		
		User user = new User(id, PasswordUtil.encryptPassword(pw), name, email, position, sido_code);
		service.regist(user);
		session.setAttribute("userInfo", user);
		
		return "redirect:/index";
	}

	
	@GetMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	private String login(String id, String pw, HttpSession session) {
		User user = service.login(id, pw);
		session.setAttribute("userInfo", user);
		return "redirect:/index";
	}
}
