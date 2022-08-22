package com.hr.reserve.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.reserve.domain.Member;
import com.hr.reserve.domain.OrderProduct;
import com.hr.reserve.domain.Orders;
import com.hr.reserve.domain.Product;
import com.hr.reserve.repository.MemberRepository;
import com.hr.reserve.repository.OrdersRepository;
import com.hr.reserve.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrdersRepository ordersRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 주문
	@Transactional
	public Long order(Long memberId, Long productId, int count) {
		// 엔티티 조회
		Member member = (Member) memberRepository.findById(memberId);
		Product product = productRepository.findOne(productId);
		
		// 주문상품 생성
		OrderProduct orderProduct = OrderProduct.createOrderProduct(product, count);
		
		// 주문 생성
		Orders order = Orders.createOrder(member, orderProduct);
		
		// 주문 저장
		ordersRepository.save(order);
		
		logger.info("register order[orderId : {}, memberLoginId : {}][{}]", order.getId(), member.getLoginId(), this.getClass().getName());
		
		return order.getId();
	}
	
	// 취소
	@Transactional
	public void cancelOrder(Long orderId) {
		// 주문 엔티티 조회
		Orders order = ordersRepository.findOne(orderId);
		
		logger.info("cancel order[orderId : {}][{}]", order.getId(), this.getClass().getName());
		
		// 주문 취소
		order.cancel(); 
	}
	
	// 검색
	//public List<Orders> fondOrders(OrderSearch orderSearch) {}
	
	
	

}
