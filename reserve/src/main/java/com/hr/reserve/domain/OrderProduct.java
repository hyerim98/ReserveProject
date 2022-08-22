package com.hr.reserve.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // createOrderProduct로만 주문상품 객체를 생성하게 하기 위하여(new로 생성하는 것은 막는다)
public class OrderProduct {

	@Id
	@GeneratedValue
	@Column(name = "orderProduct_id")
	private Long id;
	
	private int orderPrice;
	
	private int orderQuantity; // 주문 수량
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	// == 생성 메서드 == //
	public static OrderProduct createOrderProduct(Product product, int count) {
		OrderProduct orderProduct = new OrderProduct();
		
		orderProduct.setProduct(product);
		orderProduct.setOrderQuantity(count);
		
		product.removeStock(count);
		
		return orderProduct;
	}
	
	
	// == 주문 취소 == //
	public void cancel() {
		getProduct().addStock(orderQuantity);
	}
	
	public int getTotalPrice() {
		this.orderPrice = product.getPrice();
				
		return getOrderQuantity() * getOrderPrice();
	}
}
