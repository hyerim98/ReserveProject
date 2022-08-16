package com.hr.reserve.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotEnoughStockException extends RuntimeException{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public NotEnoughStockException(int quantity) {
		logger.error("[���� ����]���� ���� ��� : " + quantity);
	}
}
