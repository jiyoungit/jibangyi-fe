package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDetailDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseInfoSimpleDto;

@Mapper
public interface HouseMapper {

	List<DongCodeDto> dongCodeList(String dongName);

	List<HouseInfoDto> searchByDongCode(@Param("dongCode") String dongCode, @Param("offset") int offset,
			@Param("limit") int limit);

	List<HouseInfoSimpleDto> searchAptInfoByCoold(@Param("lat")String lat, @Param("lng")String lng, 
			@Param("range")int range, @Param("offset")int offset, @Param("limit")int limit);
	
	HouseInfoDetailDto searchDetailByAptNo(String aptCode);
	
	int getTotalAptDealsByAptCode(String aptCode);
	
	List<HouseDealDto> searchAptDeals(@Param("aptCode") String aptCode, @Param("offset") int offset,
			@Param("limit") int limit);

}
