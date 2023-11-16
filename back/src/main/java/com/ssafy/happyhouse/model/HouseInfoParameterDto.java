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
public class HouseInfoParameterDto {
	private String lat;
	private String lng;
	private int range;
	private int offset;
	private int limit;
}
