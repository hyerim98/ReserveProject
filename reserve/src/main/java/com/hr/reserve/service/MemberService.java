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

@Service // Ʈ����� ó��
@Transactional(readOnly = true) // �����ͺ��̽��� ������ ���� ������ �ݿ����� �ʵ���
@RequiredArgsConstructor // ������ ����(final �ʵ� ã�Ƽ� ������ ����)
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional // �����ͺ��̽��� �����Ͱ� ���Եǵ���(�켱��)
	public Long join(Member member) {
		validateDuplicateMember(member.getLoginId());
		
		logger.info("join member[Id : {}, LoginId : {}][{}]", member.getId(), member.getLoginId(), this.getClass().getName());
		
		return memberRepository.save(member);
	}
	
	// �α��� ���̵�� �ߺ� ���̵� ����
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
