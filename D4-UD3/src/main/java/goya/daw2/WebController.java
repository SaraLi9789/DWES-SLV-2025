package goya.daw2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer{
	
	@RequestMapping({"/", "/home"})
	public String home() {
		return "home";
		
	}
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
		
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
		
	}
	

}
