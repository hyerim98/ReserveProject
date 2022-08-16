package com.hr.reserve.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.hr.reserve.exception.NotEnoughStockException;

import lombok.Setter;

import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Product {
	
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private Long id;
	
	private String productName;
	
	private String imgUrl;
	
	private int stockQuantity;
	
	// 재고 증가
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	// 재고 감소
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		
		if(restStock < 0) {
			throw new NotEnoughStockException(this.stockQuantity);
		}
		
		this.stockQuantity -= quantity;
	}
	

}
