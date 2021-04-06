package index.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="index")
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		logger.info("info: 메인화면");
		logger.debug("debug: 메인화면");
		return "/index";
	}

}
