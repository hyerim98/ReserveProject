package com.hr.reserve.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // �Ӻ���� Ÿ���� �⺻ �����ڰ� �ʼ�
public class Address {
	private String streetName;
	private String detailAddress;
	
	// �̷������� Setter ��� �����ڷ� �ʱ�ȭ�Ͽ� ���� �Ұ����� ��ü�� ������ִ� ���� ����
	public Address(String streetName, String detailAddress) {
		this.streetName = streetName;
		this.detailAddress = detailAddress;
	}

}
