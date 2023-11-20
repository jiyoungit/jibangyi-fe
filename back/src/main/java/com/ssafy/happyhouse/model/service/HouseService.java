package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealPageDto;
import com.ssafy.happyhouse.model.HouseDealParameterDto;
import com.ssafy.happyhouse.model.HouseInfoDetailDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseInfoSimpleDto;

public interface HouseService {

	List<DongCodeDto> dongCodeList(String dongName) throws Exception;

	List<HouseInfoDto> searchByDongCode(String dongCode, int offset, int limit) throws Exception;

	HouseInfoDetailDto searchByAptNo(String aptCode);

	HouseDealPageDto searchByAptNo(HouseDealParameterDto param) throws Exception;

	List<HouseInfoSimpleDto> searchAptInfoByCoold(String lat, String lng, int range, int offset, int limit) throws Exception;

}
