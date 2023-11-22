package com.ssafy.member.model.service;

import java.util.List;

import com.ssafy.member.model.MemberHouseDto;
import com.ssafy.member.model.MemberParameter.MyHouseDto;

public interface MemberHouseService {
	void saveUserHouse(MyHouseDto myHouseDto) throws Exception;
	List<MemberHouseDto> getUserHouseList(String userId) throws Exception;
	boolean isUserHouse(MyHouseDto myHouseDto) throws Exception;
	void deleteUserHouse(MyHouseDto myHouseDto) throws Exception;
}
