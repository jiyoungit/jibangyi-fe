package com.ssafy.happyhouse.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseInfoDetailDto {
	private long aptCode;
	private String recentDealAmount;
	private String minDealAmount;
	private String maxDealAmount;
	private String avgDealAmount;
	private String aptName;
	private String address;
	private String buildYear;
	private String firstDealDate;
	private String avgArea;
}
