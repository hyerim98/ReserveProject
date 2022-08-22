package com.hr.reserve.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hr.reserve.domain.CWeight;
import com.hr.reserve.domain.Cherry;
import com.hr.reserve.domain.Member;
import com.hr.reserve.domain.OrderStatus;
import com.hr.reserve.domain.Orders;
import com.hr.reserve.domain.Product;
import com.hr.reserve.exception.NotEnoughStockException;
import com.hr.reserve.repository.OrdersRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrdersServiceTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Test
	public void ��ǰ�ֹ�() throws Exception {
		Member member = new Member();
		member.setLoginId("aaa");
		em.persist(member);
		
		Product cherry = new Cherry();
		cherry.setProductName("c500");
		cherry.setStockQuantity(50);
		cherry.setPrice(CWeight.G500);
		em.persist(cherry);
		
		int orderCount = 3;
		Long orderId = orderService.order(member.getId(), cherry.getId(), orderCount);
		
		Orders order = ordersRepository.findOne(orderId);
		
		assertEquals(OrderStatus.ORDER, order.getOrderStatus()); // �ֹ� ����
		assertEquals(1, order.getOrderProducts().size()); // �ֹ� ��ǰ ����
		assertEquals(60000, order.getTotalPrice()); // �� ����
		assertEquals(47, cherry.getStockQuantity()); // ���� ���
	}
	
	@Test
	public void �ֹ����() throws Exception {
		Member member = new Member();
		member.setLoginId("aaa");
		em.persist(member);
		
		Product cherry = new Cherry();
		cherry.setProductName("c500");
		cherry.setStockQuantity(50);
		cherry.setPrice(CWeight.G500);
		em.persist(cherry);
		
		int orderCount = 3;
		Long orderId = orderService.order(member.getId(), cherry.getId(), orderCount);
		
		orderService.cancelOrder(orderId);
		
		Orders order = ordersRepository.findOne(orderId);
		
		assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
		assertEquals(50, cherry.getStockQuantity());
	}
	
	@Test
	public void �ֹ�_�������ʰ�() throws Exception {
		Member member = new Member();
		member.setLoginId("aaa");
		em.persist(member);
		
		Product cherry = new Cherry();
		cherry.setProductName("c500");
		cherry.setStockQuantity(50);
		cherry.setPrice(CWeight.G500);
		em.persist(cherry);
		
		int orderCount = 55;
		
		NotEnoughStockException thrown = assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), cherry.getId(), orderCount));
		assertEquals(null, thrown.getMessage());
	}

}
