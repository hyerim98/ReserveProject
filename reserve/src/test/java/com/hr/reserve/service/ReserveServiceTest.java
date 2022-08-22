package com.hr.reserve.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hr.reserve.domain.Member;
import com.hr.reserve.domain.MemberType;
import com.hr.reserve.domain.Payment;
import com.hr.reserve.domain.Reserve;
import com.hr.reserve.repository.ReserveRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ReserveServiceTest {
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	EntityManager em;
	
	// 테스트 시 reserve_availability 테이블의 데이터가 계속 삭제가 되어 테스트 불가(EmptyResultDatAaccessException 에러 발생)
	@Test
	public void reserve() {
		Member member = new Member();
		member.setLoginId("aaa");
		em.persist(member);
		
		Map<MemberType, Integer> memberType = new HashMap<>();
		memberType.put(MemberType.ADULT, 3);
		memberType.put(MemberType.CHILD, 2);
		
		reserveService.reserve(member.getId(), Payment.BANK, "3", "10", memberType);
	}
	
	

}
