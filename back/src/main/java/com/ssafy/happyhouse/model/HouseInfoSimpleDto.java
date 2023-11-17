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
	private int avgDealAmount;
	private int avgArea;
	private String lng;
	private String lat;
}
