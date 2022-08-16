package com.hr.reserve.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hr.reserve.domain.Orders;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor // final �ʵ带 ã�� ������ ����
public class OrdersRepository {
	
	private final EntityManager em;
	
	public Long save(Orders order) {
		em.persist(order);
		return order.getId();
	}
	
	public Orders findOne(Long id) {
		return em.find(Orders.class, id);
	}
	
	
	// public List<Orders> findAll(OrderSearch orderSerch){}

}
