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

import pe.edu.upc.spring.model.SuscripcionXPropietario;
import pe.edu.upc.spring.model.Suscripcion;
import pe.edu.upc.spring.model.Propietario;
import pe.edu.upc.spring.service.ISuscripcionXPropietarioService;
import pe.edu.upc.spring.service.ISuscripcionService;
import pe.edu.upc.spring.service.IPropietarioService;

@Controller
@RequestMapping("/SuscripcionXPropietario")
public class SuscripcionXPropietarioController {
	@Autowired
	private ISuscripcionXPropietarioService srService;
	
	@Autowired
	private ISuscripcionService sService;
	
	@Autowired
	private IPropietarioService rService;
	
	@RequestMapping("/suscripcion")
	public String irPaginaBienvenida() {
		return "noSusP"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPublicacionPropietarios(Map<String, Object> model) {
		model.put("listaSuscripcionXRoomies", srService.listar());
		return "pSuscripcionP"; // "listPropietarios" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaSuscripciones", sService.listar());
		model.addAttribute("listaPropietarios", rService.listar());
		
		model.addAttribute("suscripcionXPropietario", new SuscripcionXPropietario());
		model.addAttribute("suscripcion", new Suscripcion());
		model.addAttribute("propietario", new Propietario());
		return "registrarSuscripcionP"; // "propietario" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute SuscripcionXPropietario objSuscripcionXPropietario, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "planSuscripcionPropietario";
		else {
			boolean flag = srService.grabar(objSuscripcionXPropietario);
			if (flag)
				return "redirect:/planSuscripcionPropietario/listar";
			else {
				model.addAttribute("mensaje", "No se pudo acceder");
				return "redirect:/planSuscripcionPropietario/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<SuscripcionXPropietario> objSuscripcionXPropietario = srService.listarId(id);
		if (objSuscripcionXPropietario == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/planSuscripcionPropietario/listar";
		}
		else {
			model.addAttribute("suscripcionXPropietario", objSuscripcionXPropietario);
			return "planSuscripcionPropietario";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				srService.eliminar(id);
				model.put("listaSuscripcionXPropietarios", srService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSuscripcionXPropietarios", srService.listar());
		}
		return "listPlanSuscripcionRoomies";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaSuscripcionXPropietarios", srService.listar());
		return "listPlanSuscripcionPropietarios";
	}
	
}
