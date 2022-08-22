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
	
	protected Reserve() {
		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "reserve_id")
	private Long id;
	
	private ReserveStatus reserveStatus;
	
	private LocalDateTime requestDT;
	
	private LocalDateTime approveDT;
	
	private int totalPrice; // MemberType Map를 조회해서 세팅
	
	public void setTotalPrice() {
		this.totalPrice = memberType.entrySet().stream().mapToInt(entry -> ((MemberType)entry.getKey()).getPrice() * entry.getValue()).sum();
	}
	
	private int pnum; // MemberType Map를 조회해서 세팅
	
	public void setPnum() {
		this.pnum = memberType.entrySet().stream().mapToInt(entry -> entry.getValue()).sum();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reserve_avail_id")
	private ReserveAvailability reserveAvailability;
	
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
	
	
	// 연관관계 메서드
	public void setMember(Member member) {
		this.member = member;
		member.getReservation().add(this);
	}
	
	// 연관관계 메서드
	public void setReserveAvailability(ReserveAvailability reserveAvailability) {
		this.reserveAvailability = reserveAvailability;
		reserveAvailability.getReservations().add(this);
	}
	
	// 예약 생성 메서드
	public static Reserve createReserve(Member member, Payment payment, String reserveDate, String reserveTime, Map<MemberType, Integer> memberType) {
		Reserve reserve = new Reserve();
		
		reserve.setMember(member);
		reserve.setReserveStatus(ReserveStatus.REQUEST);
		reserve.setPayment(payment);
		reserve.setRequestDT(LocalDateTime.now());
		reserve.setMemberType(memberType);
		
		// 예약가능한지 확인하는 로직이 필요(repository)
		reserve.setReserveDate(reserveDate);
		reserve.setReserveTime(reserveTime);
		
		return reserve;
	}
	
	// 관리자 예약 승인
	public void appove() {
		this.setReserveStatus(ReserveStatus.APPROVAL);
		
		getReserveAvailability().removeQuantity(pnum);
	}
	
	// 예약 취소
	public void cancel() {
		this.setReserveStatus(ReserveStatus.CANCEL);
		
		getReserveAvailability().addQuantity(pnum);
	}
	
}
