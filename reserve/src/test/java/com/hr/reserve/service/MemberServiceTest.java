package com.hr.reserve.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hr.reserve.domain.Member;
import com.hr.reserve.exception.DuplicateLoginIdException;
import com.hr.reserve.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void success_join() throws Exception {
		Member member = new Member();
		
		member.setName("hr");
		member.setLoginId("fla0908");
		
		Long saveId = memberService.join(member);
		
		Assertions.assertThat(member.getId()).isEqualTo(saveId);
	}
	
	@Test
	public void dup_mem_exception() throws Exception {
		Member member1 = new Member();
		member1.setLoginId("aaa");
		
		Member member2 = new Member();
		member2.setLoginId("aaa");
		
		memberService.join(member1);
		
		/* 해당 에러가 발생하는지 확인 */
		DuplicateLoginIdException thrown = 
				assertThrows(DuplicateLoginIdException.class, () -> memberService.join(member2));
		
		assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
		
	}

}
