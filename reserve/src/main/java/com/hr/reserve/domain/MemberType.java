package com.hr.reserve.domain;

import lombok.Getter;

@Getter
public enum MemberType {
	ADULT(15000), CHILD(12000), PRESCHOOLER(0);
	
	private int price;
	
	private MemberType(int price) {
		this.price = price;
	}
	
}
