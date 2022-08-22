package com.hr.reserve.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hr.reserve.domain.Orders;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor // final 필드를 찾아 생성자 주입
public class OrdersRepository {
	
	private final EntityManager em;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Long save(Orders order) {
		em.persist(order);
		
		logger.info("register order[orderId : {}, memberLoginId : {}][{}]", order.getId(), order.getMember().getLoginId(), this.getClass().getName());
		
		return order.getId();
	}
	
	public Orders findOne(Long id) {
		
		logger.info("find order by Id(PK)[Id : {}][{}]", id, this.getClass().getName());
		
		return em.find(Orders.class, id);
	}
	
	
	// public List<Orders> findAll(OrderSearch orderSerch){}

}
