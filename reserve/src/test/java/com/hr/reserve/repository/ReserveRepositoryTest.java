package com.hr.reserve.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hr.reserve.domain.Member;
import com.hr.reserve.domain.MemberType;
import com.hr.reserve.domain.Payment;
import com.hr.reserve.domain.Reserve;
import com.hr.reserve.domain.ReserveStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReserveRepositoryTest {

	@Autowired
	ReserveRepository reserveRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void reserveSave() {
		Member member = new Member();
		member.setLoginId("aaa");
		
		Map<MemberType, Integer> memberType = new HashMap<>();
		memberType.put(MemberType.ADULT, 3);
		memberType.put(MemberType.CHILD, 2);
		
		Reserve reserve = Reserve.createReserve(member, Payment.BANK, "3", "10", memberType);
		
		reserve.setTotalPrice();
		
		// 전체 가격 조회
		Assertions.assertThat(reserve.getTotalPrice()).isEqualTo(69000);
		
		reserve.setPnum();
		
		// 예약 인원 수 조회
		Assertions.assertThat(reserve.getPnum()).isEqualTo(5);
	}
	
	@Test
	@Transactional
	public void reserveFindOne() {
		Member member = new Member();
		member.setLoginId("aaa");
		
		Map<MemberType, Integer> memberType = new HashMap<>();
		memberType.put(MemberType.ADULT, 3);
		memberType.put(MemberType.CHILD, 2);
		
		Reserve reserve = Reserve.createReserve(member, Payment.BANK, "3", "10", memberType);
		em.persist(reserve);
		
		Reserve findReserve = reserveRepository.findOne(reserve.getId());
		
		Assertions.assertThat(reserve.getId()).isEqualTo(findReserve.getId());
	}
	
	
}
