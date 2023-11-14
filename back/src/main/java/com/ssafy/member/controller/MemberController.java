package com.ssafy.member.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/members")
@Api(tags = {"Members Controller API V1"})
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@ApiOperation(value = "회원 등록", notes = "입력한 사용자 정보를 바탕으로 회원 등록을 진행합니다.")
	@PostMapping("/")
	private ResponseEntity<?> join(@RequestBody MemberDto memberDto) throws Exception {
		if (memberService.idCheck(memberDto.getUserId()) == 1) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Duplicated ID.");
		}
		memberService.joinMember(memberDto);
		return ResponseEntity.ok("success join");
	}

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 입력하여 로그인 합니다.")
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody MemberDto memberDto, @ApiIgnore HttpSession session) throws Exception {
		String userId = memberDto.getUserId();
		String userPwd = memberDto.getUserPwd();
		if (userId == null || userPwd == null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Empty ID/Password.");
		
		memberDto = memberService.loginMember(userId, userPwd);
		if (memberDto != null) {
			session.setAttribute("userinfo", memberDto);
			return ResponseEntity.ok("success login");
		} else {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid ID/Password.");
		}
	}

	@ApiOperation(value = "로그아웃", notes = "로그인한 상태에서 로그아웃을 수행합니다.")
	@GetMapping("/logout")
	private ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("success logout");
	}

	@ApiOperation(value = "회원 정보 수정", notes = "로그인한 상태에서 회원 정보를 수정합니다. 로그인이 되어있어야 합니다.")
	@PutMapping("/")
	private ResponseEntity<?> edit(@RequestBody MemberDto memberDto, @ApiIgnore HttpSession session) throws Exception {
		MemberDto userDto = (MemberDto) session.getAttribute("userinfo");
		if (userDto == null)
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body("Unauthorized user.");
		memberDto.setUserId(userDto.getUserId());
		int cnt = memberService.edit(memberDto);
		if (cnt == 0)
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid ID");
		return ResponseEntity.ok("success login");
	}

	@ApiOperation(value = "회원 정보 조회", notes = "로그인한 상태에서 회원 정보를 조회합니다. 로그인이 되어있어야 합니다.")
	@GetMapping("/info")
	private ResponseEntity<?> getUserInfo(@ApiIgnore HttpSession session) throws Exception {
		MemberDto userDto = (MemberDto) session.getAttribute("userinfo");
		if (userDto == null)
			return null;
		MemberDto dto = memberService.getUserInfo(userDto.getUserId());
		return ResponseEntity.ok(dto);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "로그인한 상태에서 회원 탈퇴를 진행합니다. 로그인이 되어있어야 합니다.")
	@DeleteMapping("/")
	private ResponseEntity<?> deleteUser(@ApiIgnore HttpSession session) throws Exception {
		MemberDto userDto = (MemberDto) session.getAttribute("userinfo");
		if (userDto == null)
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body("Unauthorized user.");
		int cnt = memberService.deleteUser(userDto.getUserId());
		session.invalidate();
		return ResponseEntity.ok("Success delete");
	}
	
}
