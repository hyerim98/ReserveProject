package com.hr.reserve.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Orders {
	
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	// CascadeType.ALL : orderProduct�� set�ϰ� persist(order)�� ���ָ�, orderProduct�� ���� persist�� ���ش�
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private LocalDateTime orderDate;
	
	private int price;
	
	// �������� �޼���
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	// �������� �޼���
	public void addOrderProduct(OrderProduct orderProduct) {
		this.orderProducts.add(orderProduct);
		orderProduct.setOrder(this);
	}
	
	
	// == ���� �޼��� == //
	 public static Orders createOrder(Member member, OrderProduct... orderProducts) {
		 Orders order = new Orders();
		 
		 order.setMember(member);
		 order.setOrderStatus(OrderStatus.ORDER);;
		 order.setOrderDate(LocalDateTime.now());
		 
		 for(OrderProduct p : orderProducts) {
			 order.addOrderProduct(p);
		 }
		 
		 return order;
	 }
	 
	 
	 // == �ֹ� ��� == //
	 public void cancel(){
		 this.setOrderStatus(OrderStatus.CANCEL);
		 
		 for(OrderProduct p : orderProducts) {
			 p.cancel();
		 }
	 }
	
	 
	 // == ��ü �ֹ� ���� == //
	 public int getTotalPrice() {
		 return orderProducts.stream().mapToInt(OrderProduct::getTotalPrice).sum();
	 }
}
