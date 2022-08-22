package com.hr.reserve.service;


import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.reserve.domain.Member;
import com.hr.reserve.domain.MemberType;
import com.hr.reserve.domain.Payment;
import com.hr.reserve.domain.Reserve;
import com.hr.reserve.exception.NotEnoughReserveException;
import com.hr.reserve.repository.MemberRepository;
import com.hr.reserve.repository.ReserveRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReserveService {
	
	private final ReserveRepository reserveRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public Long reserve(Long memberId, Payment payment, String reserveDate, String reserveTime, Map<MemberType, Integer> memberType) {
		Member member = memberRepository.findById(memberId);
		
		// ���� �� �ϼ��� ����(pCount - memberType �ʱ�ȭ �ʿ�) : front ���� �� ���� ����
		if(!reserveRepository.chkReserveAvail(reserveDate, reserveTime, 0)) {
			throw new NotEnoughReserveException(reserveDate, reserveTime, 0);
		}
		
		Reserve reserve = Reserve.createReserve(member, payment, reserveDate, reserveTime, memberType);
		
		reserveRepository.save(reserve);
		
		return reserve.getId();
	}
	
	@Transactional
	public void cancelReserve(Long reserveId) {
		Reserve reserve = reserveRepository.findOne(reserveId);
		
		reserve.cancel();
	}
	
	// �˻�

}
