package com.ssafy.happyhouse.model;

import java.util.List;

import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
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
	private String aptCode;
	private int pageNo = 1;
	private int spp = 5;
}
