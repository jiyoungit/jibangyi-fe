package com.ssafy.member.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		System.out.println(userId + ":" + userPwd);
		return memberMapper.loginMember(userId, userPwd);
	}

	@Override
	public int edit(MemberDto memberDto) throws SQLException {
		return memberMapper.edit(memberDto);
	}

	@Override
	public MemberDto getUserInfo(String userId) throws SQLException {
		return memberMapper.getUserInfo(userId);
	}

	@Override
	public int deleteUser(String userId) throws SQLException {
		return memberMapper.deleteUser(userId);

	}

}
