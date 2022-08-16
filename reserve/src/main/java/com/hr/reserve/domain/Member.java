package com.hr.reserve.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	private String tel;
	
	private String email;
	
	private String loginId;
	
	private String password;
	
	@Embedded
	private Address address;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "member")
	private List<Orders> orders = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<Reserve> reservation = new ArrayList<>();
	
}
