package com.hr.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// LoggerFactory���� �ΰ� ��ü�� �ҷ��� ��, �ΰ� ��ü�� �̿��ؼ� �ڵ��� ���ϴ� �κп� �α׸� ��´�.
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("home")
	public String home(Model model) {
		
		logger.trace("Trace Level �׽�Ʈ");
        logger.debug("DEBUG Level �׽�Ʈ");
        logger.info("INFO Level �׽�Ʈ");
        logger.warn("Warn Level �׽�Ʈ");
        logger.error("ERROR Level �׽�Ʈ");

		model.addAttribute("data", "welcome!!");
		
		return "home";
	}

}
