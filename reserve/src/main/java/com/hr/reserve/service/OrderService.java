package com.hr.reserve.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.reserve.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrdersRepository ordersRepository;
	
	
	@Transactional
	public void cancelOrder(Long orderId) {
		
	}
	
	

}
