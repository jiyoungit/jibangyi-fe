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
public class HouseDealDto {

	private long 	no;
	private long 	aptCode;
	private int 	dealYear;
	private int 	dealMonth;
	private int 	dealDay;
	private String 	dealAmount;
	private String 	area;
	private String 	floor;
	private String 	cancelDealType;


}
