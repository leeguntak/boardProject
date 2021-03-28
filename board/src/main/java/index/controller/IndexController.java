package index.controller;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="index")
public class IndexController {
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {
		return "/index";
	}

}
