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
	
	private int totalPrice; // MemberType Map�� ��ȸ�ؼ� ����
	
	public void setTotalPrice() {
		this.totalPrice = memberType.entrySet().stream().mapToInt(entry -> ((MemberType)entry.getKey()).getPrice() * entry.getValue()).sum();
	}
	
	private int pnum; // MemberType Map�� ��ȸ�ؼ� ����
	
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
	
	// �ο� ��
	@ElementCollection
	@CollectionTable(
			name = "NumberOfPeople",
			joinColumns = @JoinColumn(name = "member_id"))
	private Map<MemberType, Integer> memberType = new HashMap<>(); // ADULT 3, CHILD 2
	
	
	// �������� �޼���
	public void setMember(Member member) {
		this.member = member;
		member.getReservation().add(this);
	}
	
	// �������� �޼���
	public void setReserveAvailability(ReserveAvailability reserveAvailability) {
		this.reserveAvailability = reserveAvailability;
		reserveAvailability.getReservations().add(this);
	}
	
	// ���� ���� �޼���
	public static Reserve createReserve(Member member, Payment payment, String reserveDate, String reserveTime, Map<MemberType, Integer> memberType) {
		Reserve reserve = new Reserve();
		
		reserve.setMember(member);
		reserve.setReserveStatus(ReserveStatus.REQUEST);
		reserve.setPayment(payment);
		reserve.setRequestDT(LocalDateTime.now());
		reserve.setMemberType(memberType);
		
		// ���డ������ Ȯ���ϴ� ������ �ʿ�(repository)
		reserve.setReserveDate(reserveDate);
		reserve.setReserveTime(reserveTime);
		
		return reserve;
	}
	
	// ������ ���� ����
	public void appove() {
		this.setReserveStatus(ReserveStatus.APPROVAL);
		
		getReserveAvailability().removeQuantity(pnum);
	}
	
	// ���� ���
	public void cancel() {
		this.setReserveStatus(ReserveStatus.CANCEL);
		
		getReserveAvailability().addQuantity(pnum);
	}
	
}
