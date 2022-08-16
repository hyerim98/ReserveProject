package com.hr.reserve.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 임베디드 타입은 기본 생성자가 필수
public class Address {
	private String streetName;
	private String detailAddress;
	
	// 이런식으로 Setter 대신 생성자로 초기화하여 변경 불가능한 객체를 만들어주는 것이 좋음
	public Address(String streetName, String detailAddress) {
		this.streetName = streetName;
		this.detailAddress = detailAddress;
	}

}
