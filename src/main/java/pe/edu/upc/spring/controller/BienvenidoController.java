package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bienvenido")
public class BienvenidoController {

	@RequestMapping({"/",""})
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/login")
	public String irPaginaLogin() {
		
		return "redirect:/propietario/irLogin";
	}
	
}
