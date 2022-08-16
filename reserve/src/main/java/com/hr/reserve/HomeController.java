package com.hr.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// LoggerFactory에서 로거 객체를 불러온 후, 로거 객체를 이용해서 코드의 원하는 부분에 로그를 찍는다.
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("home")
	public String home(Model model) {
		
		logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");

		model.addAttribute("data", "welcome!!");
		
		return "home";
	}

}
