package com.ssafy.member.model;

import lombok.Data;

public class MemberParameter {
	
	@Data
	public static class DupilcatedIdDto {
		String userId;
	}
	
	@Data
	public static class MyHouseDto {
		String userId;
		String aptCode;
	}
}
