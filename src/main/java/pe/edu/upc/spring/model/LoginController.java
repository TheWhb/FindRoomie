package pe.edu.upc.spring.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping({"/","/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping("/bienvenido")
	public String bienvenido() {
		return "bienvenido";
	}
}
