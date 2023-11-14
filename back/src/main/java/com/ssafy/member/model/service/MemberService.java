package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(String userId, String userPwd) throws Exception;
	int edit(MemberDto memberDto) throws SQLException;
	MemberDto getUserInfo(String userId) throws SQLException;
	int deleteUser(String userId) throws SQLException;
}
