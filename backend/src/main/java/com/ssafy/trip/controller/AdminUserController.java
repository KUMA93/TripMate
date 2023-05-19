package com.ssafy.trip.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.TripException;
import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.model.service.UserService;
import com.ssafy.trip.model.service.UserServiceImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/rest/admin")
@Api(value = "Admin User REST-ful API", tags = "관리자 유저 관리 api")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	private static final String SUCCESS = "success";
	
	@ApiOperation(value="회원 정보 조회", notes="회원 ID에 해당하는 회원 정보 조회한다.")
	@GetMapping("/user/{id}")
	public ResponseEntity<?> search(@PathVariable String id) {
		User user = service.search(id);
		
		if ( user != null ) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@ApiOperation(value="회원 목록 정보", notes="전체 회원 정보 목록을 출력한다.")
	@GetMapping("/user")
	public ResponseEntity<?> searchAll() {
		
		List<User> users = service.searchAll();
		
		if (users!=null && !users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@ApiOperation(value="회원 정보 수정", notes="회원 ID에 해당하는 회원 정보를 수정한다.")
	@PutMapping("/user")
	public ResponseEntity<String> update(@RequestBody User user) {
		
		service.update(user);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK) ;
	}
	
	
	@ApiOperation(value="회원 정보 등록", notes="회원 정보를 등록시킨다.")
	@PostMapping("/user")
	public ResponseEntity<String> regist(@RequestBody User user) {
		
		service.regist(user);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK) ;
	}
	
	@ApiOperation(value="회원 정보 삭제", notes="회원 ID에 해당하는 회원 정보를 삭제시킨다.")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> remove(@PathVariable String id) {
		
		service.remove(id);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.NO_CONTENT) ;
	}
	
}
