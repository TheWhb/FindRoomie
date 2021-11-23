package pe.edu.upc.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Roomie;
import pe.edu.upc.spring.service.IRoomieService;


@Controller
@RequestMapping
public class LoginRController {
	
	@Autowired
	private IRoomieService pService;

	@GetMapping(value = { "/loginR"})
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash ) {
		
		if (principal != null) {
			Roomie roomie = new Roomie();
			roomie = pService.findByEmail(principal.getName());
			
			return "redirect:/roomie/datos/" + roomie.getIdRoomie();
		}

		if (error != null) {
			model.addAttribute("error",	"Error desconcido");
		}

		if (logout != null) {
			model.addAttribute("success", "Se ha cerrado ssion");
			return "redirect:/bienvenido";
		}

		return "loginR";
	}

}