package com.hr.reserve.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.reserve.domain.Member;
import com.hr.reserve.exception.DuplicateLoginIdException;
import com.hr.reserve.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service // 트랜잭션 처리
@Transactional(readOnly = true) // 데이터베이스에 데이터 변경 사항이 반영되지 않도록
@RequiredArgsConstructor // 생성자 주입(final 필드 찾아서 생성자 주입)
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional // 데이터베이스에 데이터가 삽입되도록(우선권)
	public Long join(Member member) {
		validateDuplicateMember(member.getLoginId());
		
		logger.info("join member[Id : {}, LoginId : {}][{}]", member.getId(), member.getLoginId(), this.getClass().getName());
		
		return memberRepository.save(member);
	}
	
	// 로그인 아이디로 중복 아이디 방지
	public void validateDuplicateMember(String loginId) {
		List<Member> existMember = memberRepository.findByLoginId(loginId);
		
		logger.info("validateDuplicateMember by LoginId[{}][{}]", loginId, this.getClass().getName());
		
		if(!existMember.isEmpty()) {
			logger.error("exist LoginId[LoginId : {}][{}]", loginId, this.getClass().getName());
			throw new DuplicateLoginIdException(loginId);
		}
	}
	
	public List<Member> findMembers() {
		
		logger.info("select members[{}]", this.getClass().getName());
		
		return memberRepository.findAll();
	}
	
	public Member findOne(Long id) {
		
		logger.info("find member by Id(PK)[Id : {}][{}]", id, this.getClass().getName());
		
		return memberRepository.findById(id);
	}
	
}
