package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealAndInfoDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseDealParameterDto;
import com.ssafy.happyhouse.model.HouseInfoDto;

public interface HouseService {

	List<DongCodeDto> dongCodeList(String dongName) throws Exception;

	List<HouseInfoDto> searchByDongCode(String dongCode, int offset, int limit) throws Exception;

	List<HouseDealDto> searchByAptNo(String aptCode, int year, int month, int offset, int limit) throws Exception;
	
	List<HouseDealAndInfoDto> searchBySigunguCode(int sigunguCode, int dealYear, int dealMonth) throws Exception;

	List<HouseDealDto> searchAptDeals(HouseDealParameterDto param);

}
