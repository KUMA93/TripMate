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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.model.service.UserService;
import com.ssafy.trip.model.service.UserServiceImp;
import com.ssafy.trip.util.PasswordUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController

@RequestMapping("/rest")
@Api(value = "User REST-ful API", tags = "유저 api")
public class UserRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
	private static final String SUCCESS = "success";
	
	@Autowired
	private UserService service;
	
	@ApiOperation(value="비밀번호 변경", notes="회원 ID에 해당하는 비밀번호를 변경한다.")
	@PutMapping("/user/{id}")
	private ResponseEntity<String> updatePW(@PathVariable String id, String pw) {
		User user = service.search(id);
		
		user.setPass(PasswordUtil.encryptPassword(pw));
		service.update(user);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="비밀번호 찾기", notes="ID와 이메일에 해당하는 아이디가 있다면 리턴한다.")
	@GetMapping("/user/{id}/{email}")
	private ResponseEntity<Model> findPw(@PathVariable String id, @PathVariable String email, Model model) {
		User user = service.search(id);
		
		
		if (user == null) {
			model.addAttribute("msg", "등록되지 않은 id입니다.");
			return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
		}
		
		if (!email.equals(user.getEmail())) {
			model.addAttribute("msg", "이메일이 일치하지 않습니다.");
			return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
		}
		model.addAttribute("id", id);
		return new ResponseEntity<Model>(model, HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 탈퇴", notes="회원 ID에 해당하는 회원 정보를 삭제한다.")
	@DeleteMapping("/user")
	private ResponseEntity<String> remove(HttpSession session) {
		String id = getUser(session).getId();
		service.remove(id);
		session.invalidate();
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 정보 변경", notes="로그인 중인 회원의 이름, 이메일, 비밀번호를 변경한다.")
	@PutMapping("/user")
	private ResponseEntity<String> update(String user_name, String user_email, String nextPw, HttpSession session){
		User user = getUser(session);
		
		User next = new User(user.getId(), PasswordUtil.encryptPassword(nextPw), 
				user_name, user_email, user.getPosition(), user.getSido_code());
		service.update(next);
		session.invalidate();
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	private User getUser(HttpSession session) {
		User user = (User) session.getAttribute("userInfo");
		
		return user;
	}
	
	@ApiOperation(value="회원 가입", notes="입력 받은 정보로 회원가입을 한다.")
	@PostMapping("/user")
	private ResponseEntity<String> regist(String id, String pw, String name, String email, HttpSession session){
		String position = "user";
		int sido_code = 12;
		
		User user = new User(id, PasswordUtil.encryptPassword(pw), name, email, position, sido_code);
		service.regist(user);
		session.setAttribute("userInfo", user);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}


	@ApiOperation(value="로그인", notes="입력받은 ID와 PW의 유효성을 검사하고 로그인 한다.")
	@PostMapping("/user/login")
	private ResponseEntity<?> login(String id, String pw, HttpSession session) {
		log.debug(id, pw);
		User user = service.login(id, pw);
		if (user != null) {
			session.setAttribute("userInfo", user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
