package pe.edu.upc.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Propietario;
import pe.edu.upc.spring.service.IPropietarioService;


@Controller
@RequestMapping
public class LoginPController {
	
	@Autowired
	private IPropietarioService pService;

	@GetMapping(value = { "/loginP"})
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash ) {
		
		if (principal != null) {
			Propietario propietario = new Propietario();
			propietario = pService.findByEmail(principal.getName());
			
			return "redirect:/vivienda/datos/" + propietario.getIdPropietario();
		}

		if (error != null) {
			model.addAttribute("error",	"Error desconcido");
		}

		if (logout != null) {
			model.addAttribute("success", "Se ha cerrado ssion");
			return "redirect:/bienvenido";
		}

		return "loginP";
	}

}
