package com.hr.reserve.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Reserve {
	@Id
	@GeneratedValue
	@Column(name = "reserve_id")
	private Long id;
	
	private boolean approval;
	
	private LocalDateTime requestDT;
	
	private LocalDateTime approveDT;
	
	private int price; // MemberType Map를 조회해서 세팅
	
	private int pnum; // MemberType Map를 조회해서 세팅
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@Enumerated(EnumType.STRING)
	private Payment payment;
	
	private String reserveDate;
	private String reserveTime;
	
	// 인원 수
	@ElementCollection
	@CollectionTable(
			name = "NumberOfPeople",
			joinColumns = @JoinColumn(name = "member_id"))
	private Map<MemberType, Integer> memberType = new HashMap<>(); // ADULT 3, CHILD 2
	
}
