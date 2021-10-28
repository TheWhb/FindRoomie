package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Roomie;
import pe.edu.upc.spring.model.Vivienda;

import pe.edu.upc.spring.service.IRoomieService;
import pe.edu.upc.spring.service.IViviendaService;

@Controller
@RequestMapping("/roomie")
public class RoomieController {
	@Autowired
	private IRoomieService rService;
	
	@Autowired
	private IViviendaService vService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoRoomies(Map<String, Object> model) {
		model.put("listaRoomies", rService.listar());
		return "listRoomies"; // "listRoomies" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irLogin")
	public String irPaginaLogin() {
		
		return "loginR";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("roomie", new Roomie());
		return "registroR"; // "roomie" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Roomie objRoomie, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "registroR";
		else {
			objRoomie.setViviendaRoomie(null);
			boolean flag = rService.grabar(objRoomie);
			if (flag)
				return "redirect:/roomie/irLogin";
			else {
				model.addAttribute("mensaje", "No se pudo acceder");
				return "redirect:/roomie/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Roomie> objRoomie = rService.listarId(id);
		if (objRoomie == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/propietario/listar";
		}
		else {
			model.addAttribute("roomie", objRoomie);
			return "roomie";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRoomies", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaRoomies", rService.listar());
		}
		return "listRoomies";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaRoomies", rService.listar());
		return "listRoomies";
	}
	
}
