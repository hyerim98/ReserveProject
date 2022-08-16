package com.hr.reserve.domain;

import lombok.Getter;

@Getter
public enum CWeight {
	G500(20000), KG1(35000);
	
	private int price;
	
	private CWeight(int price) {
		this.price = price;
	}
}
