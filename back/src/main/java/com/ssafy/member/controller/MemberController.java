package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberHouseDto;
import com.ssafy.member.model.MemberParameter;
import com.ssafy.member.model.MemberParameter.MyHouseDto;
import com.ssafy.member.model.service.MemberHouseService;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class MemberController {
	
	private MemberService memberService;
	private MemberHouseService memberHouseService;
	private JWTUtil jwtUtil;

	public MemberController(MemberService memberService, MemberHouseService memberHouseService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.memberHouseService = memberHouseService;
		this.jwtUtil = jwtUtil;
	}

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		log.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
//				log.debug("access token : {}", accessToken);
//				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);
				
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원가입", notes = "입력받은 회원정보로 로그인.")
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(
			@RequestBody @ApiParam(value = "회원가입 시 필요한 회원정보(아이디, 비밀번호, 이메일 아이디, 이메일 도메인, 이름).", required = true) MemberDto memberDto) {
		log.debug("register user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.register(memberDto);
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.debug("회원가입 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아이디 중복 체크", notes = "해당 아이디가 이미 있는지 확인.")
	@PostMapping("/checkDuplicate")
	public ResponseEntity<Map<String, Object>> checkDuplicate(
			@RequestBody @ApiParam(value = "중복 체크를 할 아이디.", required = true) MemberParameter.DupilcatedIdDto dto) {
		String userId = dto.getUserId();
		log.debug("check user : {}", userId);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			boolean isDuplicated = memberService.checkDuplicate(userId);
			resultMap.put("isDuplicated", isDuplicated);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("아이디 중복 체크 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아파트 즐겨찾기 등록", notes = "사용자 아이디와 아파트 코드 등록")
	@PostMapping("/myhouse")
	public ResponseEntity<Map<String, Object>> saveHouse(
			@RequestBody @ApiParam(value = "즐겨찾기 등록에 필요한 사용자 아이디, 아파트 코드", required = true) 
			MyHouseDto myHouseDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberHouseService.saveUserHouse(myHouseDto);
			status = HttpStatus.CREATED;
		} catch (SQLException e) {
			log.debug("DB 에러 발생 : {}", e);
			resultMap.put("message", "중복된 데이터 입니다.");
			status = HttpStatus.CONFLICT;
		} catch (Exception e) {
			log.debug("알 수 없는 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아파트 즐겨찾기 목록", notes = "사용자 아이디로 아파트 즐겨찾기 데이터를 가져온다.")
	@GetMapping("/myhouse")
	public ResponseEntity<Map<String, Object>> getHouseList(
			@RequestParam @ApiParam(value = "사용자 아이디", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			System.out.println("123");
			List<MemberHouseDto> list = memberHouseService.getUserHouseList(userId);
			System.out.println(list);
			resultMap.put("userHouseList", list);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("알 수 없는 에러 발생 : {}", e);
			resultMap.put("messagee", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아파트 즐겨찾기 확인", notes = "등록된 즐겨찾기가 ")
	@GetMapping("/myhouse/checkmyhouse")
	public ResponseEntity<Map<String, Object>> checkHouse(
			@ModelAttribute @ApiParam(value = "사용자 아이디와 아파트 코드", required = true) MyHouseDto myHouseDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			boolean isUserHouse = memberHouseService.isUserHouse(myHouseDto);
			resultMap.put("isUserHouse", isUserHouse);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("알 수 없는 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아파트 즐겨찾기 삭제", notes = "사용자 아이디로 아파트 코드로 즐겨찾기 삭제")
	@DeleteMapping("/myhouse/userid/{userid}/apt/{aptcode}")
	public ResponseEntity<Map<String, Object>> removeHouse(
			@PathVariable("userid") @ApiParam(value = "사용자 아이디", required = true) String userid,
		@PathVariable("aptcode") @ApiParam(value = "아파트 코드", required = true) String aptcode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MyHouseDto dto = new MyHouseDto();
			dto.setAptCode(aptcode);
			dto.setUserId(userid);
			memberHouseService.deleteUserHouse(dto);
			status = HttpStatus.NO_CONTENT;
		} catch (SQLException e) {
			log.debug("DB 에러 발생 : {}", e);
			resultMap.put("message", "e");
			status = HttpStatus.NOT_FOUND;
		} catch (Exception e) {
			log.debug("알 수 없는 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
