package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseDealPageDto;
import com.ssafy.happyhouse.model.HouseDealParameterDto;
import com.ssafy.happyhouse.model.HouseInfoDetailDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseInfoSimpleDto;
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
	public HouseDealPageDto searchByAptNo(HouseDealParameterDto param) throws Exception{
		
		String aptCode = param.getAptCode();
		int pageNo = param.getPageNo();
		int spp = param.getSpp();
		int offset = (pageNo - 1) * spp;
		int limit = spp;

		int totalSize = mapper.getTotalAptDealsByAptCode(aptCode);
		int lastPageNo = totalSize / spp + 1;
		
		List<HouseDealDto> list = mapper.searchAptDeals(aptCode, offset, limit);
		HouseDealPageDto houseDealPageDto = new HouseDealPageDto();
		houseDealPageDto.setDealList(list);
		houseDealPageDto.setCurrentpageNo(pageNo);
		houseDealPageDto.setLastPageNo(lastPageNo);
		
		return houseDealPageDto;
	}

	@Override
	public List<HouseInfoSimpleDto> searchAptInfoByCoold(String lat, String lng, int range, int offset, int limit) throws Exception {
		return mapper.searchAptInfoByCoold(lat, lng, range, offset, limit);
	}

}
