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

import pe.edu.upc.spring.model.PublicacionRoomie;
import pe.edu.upc.spring.model.Roomie;
import pe.edu.upc.spring.service.IPublicacionRoomieService;
import pe.edu.upc.spring.service.IRoomieService;

@Controller
@RequestMapping("/publicacionRoomie/")
public class PublicacionRoomieController {
	@Autowired
	private IPublicacionRoomieService prService;
	
	@Autowired
	private IRoomieService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPublicacionRoomies(Map<String, Object> model) {
		model.put("listaPublicacionRoomies", prService.listar());
		return "listPublicacionRoomies"; // "listPropietarios" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaRoomies", rService.listar());
		
		model.addAttribute("publicacionRoomie", new PublicacionRoomie());
		model.addAttribute("roomie", new Roomie());
		return "publicacionRoomie"; // "propietario" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute PublicacionRoomie objPublicacionRoomie, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "publicacionRoomie";
		else {
			boolean flag = prService.grabar(objPublicacionRoomie);
			if (flag)
				return "redirect:/publicacionRoomie/listar";
			else {
				model.addAttribute("mensaje", "No se pudo acceder");
				return "redirect:/PublicacionRoomie/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<PublicacionRoomie> objPublicacionRoomie = prService.listarId(id);
		if (objPublicacionRoomie == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/publicacionRoomie/listar";
		}
		else {
			model.addAttribute("publicacionRoomie", objPublicacionRoomie);
			return "publicacionRoomie";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				prService.eliminar(id);
				model.put("listaPublicacionRoomies", prService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPublicacionRoomies", prService.listar());
		}
		return "listPublicacionRoomies";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaPublicacionRoomies", prService.listar());
		return "listPublicacionRoomies";
	}
	
}
