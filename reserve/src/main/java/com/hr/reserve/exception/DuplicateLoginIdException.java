package com.hr.reserve.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateLoginIdException extends RuntimeException{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public DuplicateLoginIdException(String loginId) {
		logger.error("' " + loginId + " ' �� �̹� �����ϴ� ���̵��Դϴ�.");
	}
}
