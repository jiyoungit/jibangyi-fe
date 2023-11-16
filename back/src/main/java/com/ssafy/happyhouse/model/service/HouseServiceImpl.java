package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealAndInfoDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseDealParameterDto;
import com.ssafy.happyhouse.model.HouseInfoDetailDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseMapper mapper;

	@Override
	public List<DongCodeDto> dongCodeList(String dongName) throws Exception{
		return mapper.dongCodeList(dongName);
	}

	@Override
	public List<HouseInfoDto> searchByDongCode(String dongCode, int offset, int limit) throws Exception{
		return mapper.searchByDongCode(dongCode, offset, limit);
	}

	@Override
	public HouseInfoDetailDto searchByAptNo(String aptCode) {
		return mapper.searchDetailByAptNo(aptCode);
	}
	
	@Override
	public List<HouseDealDto> searchByAptNo(String aptCode, int offset, int limit) throws Exception{
		return mapper.searchByAptNo(aptCode, offset, limit);
	}

	@Override
	public List<HouseDealAndInfoDto> searchBySigunguCode(int sigunguCode, int dealYear, int dealMonth) throws Exception{
		return mapper.searchBySigunguCode(sigunguCode, dealYear, dealMonth);
	}

	@Override
	public List<HouseDealDto> searchAptDeals(HouseDealParameterDto param) {
		return mapper.searchAptDeals(param);
	}

	@Override
	public List<HouseInfoDto> searchAptInfoByCoold(String lat, String lng, int range, int offset, int limit) throws Exception {
		return mapper.searchAptInfoByCoold(lat, lng, range, offset, limit);
	}



}
