package com.ssafy.trip.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.model.service.JwtServiceImpl;
import com.ssafy.trip.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/rest/user")
@Api("사용자 컨트롤러  API V1")
@CrossOrigin("*")
public class UserRestController {

	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(user);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
				userService.saveRefreshToken(user.getId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = userService.userInfo(id);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{id}")
	public ResponseEntity<?> removeToken(@PathVariable("id") String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, user);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(user.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//
//	@ApiOperation(value="비밀번호 변경", notes="회원 ID에 해당하는 비밀번호를 변경한다.")
//	@PutMapping("changepw/{id}")
//	private ResponseEntity<String> updatePW(@PathVariable String id, String pw) {
//		User user = service.search(id);
//		
//		user.setPass(PasswordUtil.encryptPassword(pw));
//		service.update(user);
//		
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="비밀번호 찾기", notes="ID와 이메일에 해당하는 아이디가 있다면 리턴한다.")
//	@GetMapping("/user/{id}/{email}")
//	private ResponseEntity<Model> findPw(@PathVariable String id, @PathVariable String email, Model model) {
//		User user = service.search(id);
//		
//		
//		if (user == null) {
//			model.addAttribute("msg", "등록되지 않은 id입니다.");
//			return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
//		}
//		
//		if (!email.equals(user.getEmail())) {
//			model.addAttribute("msg", "이메일이 일치하지 않습니다.");
//			return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
//		}
//		model.addAttribute("id", id);
//		return new ResponseEntity<Model>(model, HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="회원 탈퇴", notes="회원 ID에 해당하는 회원 정보를 삭제한다.")
//	@DeleteMapping("/user")
//	private ResponseEntity<String> remove(HttpSession session) {
//		String id = getUser(session).getId();
//		service.remove(id);
//		session.invalidate();
//		
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="회원 정보 변경", notes="로그인 중인 회원의 이름, 이메일, 비밀번호를 변경한다.")
//	@PutMapping("/user")
//	private ResponseEntity<String> update(String user_name, String user_email, String nextPw, HttpSession session){
//		User user = getUser(session);
//		
//		User next = new User(user.getId(), PasswordUtil.encryptPassword(nextPw), 
//				user_name, user_email, user.getPosition(), user.getSido_code());
//		service.update(next);
//		session.invalidate();
//		
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
//
//	private User getUser(HttpSession session) {
//		User user = (User) session.getAttribute("userInfo");
//		
//		return user;
//	}
//	
	@ApiOperation(value="회원 가입", notes="입력 받은 정보로 회원가입을 한다.")
	@PostMapping("/regist")
	private ResponseEntity<String> regist(@RequestBody User user){
		user.setPosition("user");
		user.setSido_code(12);
		
		userService.regist(user);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}


	
}
