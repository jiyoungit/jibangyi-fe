package com.ssafy.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberHouseDto;
import com.ssafy.member.model.MemberParameter.MyHouseDto;

@Mapper
public interface MemberHouseMapper {
	void saveUserHouse(MyHouseDto myHouseDto) throws Exception;
	List<MemberHouseDto> getUserHouseList(String userId) throws Exception;
	int isUserHouse(MyHouseDto myHouseDto) throws Exception;
	int deleteUserHouse(MyHouseDto myHouseDto) throws Exception;
}
