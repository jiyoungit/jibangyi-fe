package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberHouseDto;
import com.ssafy.member.model.MemberParameter.MyHouseDto;
import com.ssafy.member.model.mapper.MemberHouseMapper;

@Service
public class MemberHouseServiceImpl implements MemberHouseService {

	MemberHouseMapper mapper;

	public MemberHouseServiceImpl(MemberHouseMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void saveUserHouse(MyHouseDto myHouseDto) throws Exception {
		mapper.saveUserHouse(myHouseDto);
	}

	@Override
	public List<MemberHouseDto> getUserHouseList(String userId) throws Exception {
		System.out.println("2323");
		return mapper.getUserHouseList(userId);
	}

	@Override
	public boolean isUserHouse(MyHouseDto myHouseDto) throws Exception {
		int cnt = mapper.isUserHouse(myHouseDto);
		return cnt == 1;
	}

	@Override
	public void deleteUserHouse(MyHouseDto myHouseDto) throws Exception {
		int cnt = mapper.deleteUserHouse(myHouseDto);
		if (cnt == 0)
			throw new SQLException("삭제 대상 데이터가 없습니다.");
	}

}
