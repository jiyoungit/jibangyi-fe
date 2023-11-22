package com.ssafy.member.model;

import com.ssafy.happyhouse.model.HouseInfoDetailDto;

import lombok.Data;

@Data
public class MemberHouseDto {
	private String userId;
	private HouseInfoDetailDto houseInfoDetail;
}
