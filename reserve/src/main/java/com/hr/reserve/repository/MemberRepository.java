package com.hr.reserve.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hr.reserve.domain.Member;

@Repository // Entity�� ã���ִ� ��(DAO)
public class MemberRepository {

	@PersistenceContext // Entity Manager ����
	private EntityManager em;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public Long save(Member member) {
		em.persist(member);
		
		logger.info("join member[Id : {}, LoginId : {}][{}]", member.getId(), member.getLoginId(), this.getClass().getName());
		
		return member.getId();
	}
	
	// PK�� ��ȸ
	public Member findById(Long id) {
		
		logger.info("find member by Id(PK)[Id : {}][{}]", id, this.getClass().getName());
		
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() {
		String query = "select m from Member m";
		
		logger.info("select members[{}]", this.getClass().getName());
		
		return em.createQuery(query, Member.class).getResultList();
	}
	
	public List<Member> findByLoginId(String loginId) {
		String query = "select m from Member m where m.loginId = :loginId";
		
		logger.info("find member by loginId[LoginId : {}][{}]", loginId, this.getClass().getName());
		
		return em.createQuery(query, Member.class).setParameter("loginId", loginId).getResultList();
	}
	
}
