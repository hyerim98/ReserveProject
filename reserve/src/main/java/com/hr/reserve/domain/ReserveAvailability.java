package com.hr.reserve.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hr.reserve.exception.NotEnoughReserveException;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "reserve_availability")
@Getter @Setter
public class ReserveAvailability {
	
	@Id
	@GeneratedValue
	@Column(name = "reserve_avail_id")
	private Long id;
	
	private String reserveDate;
	
	private String reserveTime;
	
	private int pQuantity;
	
	@OneToMany(mappedBy = "reserveAvailability")
	private List<Reserve> reservations = new ArrayList<>();
	
	public void addQuantity(int quantity) {
		this.pQuantity += quantity;
	}
	
	public void removeQuantity(int quantity) {
		int rest = this.pQuantity - quantity;
		
		if(rest < 0) {
			throw new NotEnoughReserveException(this.reserveDate, this.reserveTime, this.pQuantity);
		}
		
		this.pQuantity -= quantity;
	}

}
