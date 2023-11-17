package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.HouseDealPageDto;
import com.ssafy.happyhouse.model.HouseDealParameterDto;
import com.ssafy.happyhouse.model.HouseInfoDetailDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseInfoSimpleDto;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	@GetMapping(value = {"/dongcodes/{dongName}", "/dongcodes"})
	public List<DongCodeDto> dongCodeList(@PathVariable(required = false) String dongName) throws Exception {
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
	public List<HouseInfoSimpleDto> searchAptInfoByCoold(
			@RequestParam(required = true) String lat, 
			@RequestParam(required = true) String lng, 
			@RequestParam(required = true) int range, 
			@RequestParam(required = false, defaultValue = RECORD_OFFSET) int offset, 
			@RequestParam(required = false, defaultValue = RECORD_LIMIT) int limit
			) throws Exception {
		return hService.searchAptInfoByCoold(lat, lng, range, offset, limit);
	}

	@ApiOperation(value = "아파트 코드 기반 아파트 거래 목록", notes = "아파트 번호와 현재 페이지에 해당하는 아파트 매매 정보 목록을 반환합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "aptCode"
	        , value = "아파트 코드"
	        , required = true
	        , dataType = "string"
	        , defaultValue = "None"),
		@ApiImplicitParam(name = "pageNo"
	        , value = "요청 페이지 번호"
	        , required = false
	        , dataType = "string"
	        , defaultValue = "1"),
		@ApiImplicitParam(name = "spp"
	        , value = "페이지 당 요소 개수"
	        , required = false
	        , dataType = "string"
	        , defaultValue = "5")
		}
	)
	@GetMapping("/apt-deals")
	public HouseDealPageDto searchByAptNo(HouseDealParameterDto param) throws Exception {
		return hService.searchByAptNo(param);
	}

	@ApiOperation(value = "아파트 코드 기반 아파트 거래 기본 정보", notes = "아파트 번호로 해당 아파트에 대한 기본 정보를 반환합니다.")
	@GetMapping("/apt-deals/info/{aptCode}")
	public HouseInfoDetailDto selectByAptNo(
			@PathVariable(required = true) String aptCode 
			) throws Exception {
		return hService.searchByAptNo(aptCode);
	}

}
