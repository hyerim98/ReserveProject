package com.hr.reserve.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotEnoughReserveException extends RuntimeException{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public NotEnoughReserveException(String reserveDate, String reserveTime, int quantity) {
		logger.error("[���� �Ұ�]���� {}�� {}�ÿ� ���� �ڸ� : {}", reserveDate, reserveTime, quantity);
	}
}
