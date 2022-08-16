package com.hr.reserve.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("C")
@Getter @Setter
public class Cherry extends Product{
	@Enumerated(EnumType.STRING)
	private CWeight weight;
}
