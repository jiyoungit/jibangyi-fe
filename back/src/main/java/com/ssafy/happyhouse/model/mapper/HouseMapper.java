package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealAndInfoDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseDealParameterDto;
import com.ssafy.happyhouse.model.HouseInfoDto;

@Mapper
public interface HouseMapper {

	List<DongCodeDto> dongCodeList(String dongName);

	List<HouseInfoDto> searchByDongCode(@Param("dongCode") String dongCode, @Param("offset") int offset,
			@Param("limit") int limit);

	List<HouseDealDto> searchByAptNo(@Param("aptCode") String aptCode, @Param("year") int year,
			@Param("month") int month, @Param("offset") int offset, @Param("limit") int limit);

	List<HouseDealAndInfoDto> searchBySigunguCode(@Param("sCode") int sigunguCode, @Param("dYear") int dealYear,
			@Param("dMonth") int dealMonth);

	List<HouseDealDto> searchAptDeals(HouseDealParameterDto param);

}
