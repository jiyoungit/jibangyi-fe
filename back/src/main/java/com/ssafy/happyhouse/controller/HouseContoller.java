package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealAndInfoDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDetailDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(tags = { "House Controller API" })
@RequestMapping("/house")
public class HouseContoller {
	
	private static final String RECORD_OFFSET = "0";
	private static final String RECORD_LIMIT = "50";

	@Autowired
	HouseService hService;

	@ApiOperation(value = "동 코드 목록", notes = "동 이름으로 검색한 결과 목록을 반환합니다.")
	@GetMapping("/dongcodes/{dongName}")
	public List<DongCodeDto> dongCodeList(@PathVariable String dongName) throws Exception {
		return hService.dongCodeList(dongName);
	}

	@ApiOperation (value ="동코드 기반 아파트 정보 목록", notes = "동 코드로 검색한 결과 목록을 반환합니다.")
	@GetMapping("/apt-infos")
	public List<HouseInfoDto> searchByDongCode(
			@RequestParam(required = true) String dongCode, 
			@RequestParam(required = false, defaultValue = RECORD_OFFSET) int offset, 
			@RequestParam(required = false, defaultValue = RECORD_LIMIT) int limit
			) throws Exception{
		return hService.searchByDongCode(dongCode, offset, limit);
	}

	@ApiOperation(value = "위치 기반 아파트 정보 목록", notes = "위도, 경도 좌표 기반으로 주변 아파트 정보 목록을 반환합니다.")
	@GetMapping("/apt-infos/coold")
	public List<HouseInfoDto> searchAptInfoByCoold(
			@RequestParam(required = true) String lat, 
			@RequestParam(required = true) String lng, 
			@RequestParam(required = true) int range, 
			@RequestParam(required = false, defaultValue = RECORD_OFFSET) int offset, 
			@RequestParam(required = false, defaultValue = RECORD_LIMIT) int limit
			) throws Exception {
		return hService.searchAptInfoByCoold(lat, lng, range, offset, limit);
	}

	@ApiOperation(value = "아파트 코드 기반 아파트 거래 목록", notes = "아파트 번호, 거래 시기로 검색한 결과 목록을 반환합니다.")
	@GetMapping("/apt-deals")
	public List<HouseDealDto> searchByAptNo(
			@RequestParam(required = true) String aptCode, 
			@RequestParam(required = false, defaultValue = RECORD_OFFSET) int offset, 
			@RequestParam(required = false, defaultValue = RECORD_LIMIT) int limit
			) throws Exception {
		return hService.searchByAptNo(aptCode, offset, limit);
	}

	@ApiOperation(value = "아파트 코드 기반 아파트 거래 기본 정보", notes = "아파트 번호, 거래 시기로 검색한 결과 목록을 반환합니다.")
	@GetMapping("/apt-deals/info")
	public HouseInfoDetailDto searchByAptNo(
			@RequestParam(required = true) String aptCode 
			) throws Exception {
		return hService.searchByAptNo(aptCode);
	}

}
