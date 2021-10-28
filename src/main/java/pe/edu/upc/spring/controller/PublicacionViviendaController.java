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

import pe.edu.upc.spring.model.PublicacionVivienda;
import pe.edu.upc.spring.model.Vivienda;
import pe.edu.upc.spring.service.IPublicacionViviendaService;
import pe.edu.upc.spring.service.IViviendaService;

@Controller
@RequestMapping("/publicacionVivienda/")
public class PublicacionViviendaController {
	@Autowired
	private IPublicacionViviendaService prService;
	
	@Autowired
	private IViviendaService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPublicacionRoomies(Map<String, Object> model) {
		model.put("listaPublicacionVivienda", prService.listar());
		return "listPublicacionViviendas"; // "listPropietarios" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaViviendas", rService.listar());
		
		model.addAttribute("publicacionVivienda", new PublicacionVivienda());
		model.addAttribute("vivienda", new Vivienda());
		return "publicacionVivienda"; // "propietario" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute PublicacionVivienda objPublicacionVivienda, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "publicacionVivienda";
		else {
			boolean flag = prService.grabar(objPublicacionVivienda);
			if (flag)
				return "redirect:/publicacionVivienda/listar";
			else {
				model.addAttribute("mensaje", "No se pudo acceder");
				return "redirect:/publicacionVivienda/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<PublicacionVivienda> objPublicacionVivienda = prService.listarId(id);
		if (objPublicacionVivienda == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/publicacionVivienda/listar";
		}
		else {
			model.addAttribute("publicacionVivienda", objPublicacionVivienda);
			return "publicacionVivienda";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				prService.eliminar(id);
				model.put("listaPublicacionViviendas", prService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPublicacionViviendas", prService.listar());
		}
		return "listPublicacionViviendas";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaPublicacionViviendas", prService.listar());
		return "listPublicacionViviendas";
	}
	
}
