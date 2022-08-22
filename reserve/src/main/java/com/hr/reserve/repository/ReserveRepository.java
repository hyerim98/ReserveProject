package com.hr.reserve.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hr.reserve.domain.Reserve;
import com.hr.reserve.domain.ReserveAvailability;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReserveRepository {

	private final EntityManager em;
	
	public void save(Reserve reserve) {
		em.persist(reserve);
	}
	
	public Reserve findOne(Long reserveId) {
		return em.find(Reserve.class, reserveId);
	}
	
	// findAll
	
	public boolean chkReserveAvail(String reserveDate, String reserveTime, int pCount) {
		
		String query = "select r from reserve_availability r where r.reserveDate = :reserveDate and r.reserveTime = :reserveTime";
		
		ReserveAvailability availability = em.createQuery(query, ReserveAvailability.class)
												.setParameter("reserveDate", reserveDate)
												.setParameter("reserveTime", reserveTime)
												.getSingleResult();
		
		int availCount = availability.getPQuantity();
		int rest = availCount - pCount;
		
		if(rest < 0) {
			return false;
		}
		
		
		return true;
		
	}
}
