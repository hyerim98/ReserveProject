package com.hr.reserve.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Getter @Setter
public class OrderProduct {
	
	public OrderProduct() {
		if(getProduct() instanceof Cherry) {
			this.orderPrice = ((Cherry)getProduct()).getWeight().getPrice();
		}
		
		// �ٸ� Product ���� �ʱ�ȭ
	}

	@Id
	@GeneratedValue
	@Column(name = "orderProduct_id")
	private Long id;
	
	private int orderPrice; // product�� Dtype(20000)���� ���
	
	private int orderQuantity; // �ֹ� ����
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	// == ���� �޼��� == //
	public OrderProduct createOrderProduct(Product product, int count) {
		OrderProduct orderProduct = new OrderProduct();
		
		orderProduct.setProduct(product);
		orderProduct.setOrderQuantity(count);
		
		product.removeStock(count);
		
		return orderProduct;
	}
	
	
	// == �ֹ� ��� == //
	public void cancel() {
		getProduct().addStock(orderQuantity);
	}
	
	public int getTotalPrice() {
		return getOrderQuantity() * getOrderPrice();
	}
}
