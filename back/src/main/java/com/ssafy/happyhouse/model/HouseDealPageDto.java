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
public class HouseDealPageDto {
	int currentpageNo;
	int lastPageNo;
	List<HouseDealDto> dealList;
}
