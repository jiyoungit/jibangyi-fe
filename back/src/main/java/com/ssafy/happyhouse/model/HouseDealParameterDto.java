package com.ssafy.happyhouse.model;

import org.springframework.lang.NonNull;

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
public class HouseDealParameterDto {
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String aptCode;
	private int year;
	private int month;
	private int pageNo;
	private int spp;
}
