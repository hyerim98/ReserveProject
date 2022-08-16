package com.hr.reserve.repository;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hr.reserve.domain.Member;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@Transactional
	public void memberTest() { // Member Insert, select가 잘되는지 확인
		Member member = new Member();
		member.setName("hyerim");
		
		Long id = memberRepository.save(member);
		
		Member findMember = memberRepository.findById(id);
		
		Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
	}
}
