package com.ssafy.happyhouse.model;

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
public class HouseInfoSimpleDto {
	private long aptCode;
	private String aptName;
	private String recentDealAmount;
	private String area;
	private String lng;
	private String lat;
}
