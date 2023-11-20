package com.ssafy.member.model.service;

import java.util.HashMap;
import java.util.Map;

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
	public MemberDto login(MemberDto memberDto) throws Exception {
		return memberMapper.login(memberDto);
	}
	
	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

	@Override
	public void register(MemberDto memberDto) throws Exception {
		String id = memberMapper.getUserId(memberDto.getUserId());
		if (id != null)
			throw new Exception("중복된 아이디입니다.");
		int cnt = memberMapper.register(memberDto);
		if (cnt == 0)
			throw new Exception("회원가입에 실패했습니다.");
	}

	@Override
	public boolean checkDuplicate(String userId) throws Exception {
		String id = memberMapper.getUserId(userId);
		if (id != null)
			return true;
		return false;
	}

}