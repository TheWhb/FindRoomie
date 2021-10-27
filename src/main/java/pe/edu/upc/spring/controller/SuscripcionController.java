package pe.edu.upc.spring.controller;

import java.util.List;
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

import pe.edu.upc.spring.model.Suscripcion;
import pe.edu.upc.spring.service.ISuscripcionService;

@Controller
@RequestMapping("/suscripcion")
public class SuscripcionController {
	@Autowired
	private ISuscripcionService sService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSuscripciones(Map<String, Object> model) {
		model.put("listaSuscripciones", sService.listar());
		return "listSuscripciones"; // "listSuscripciones" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("suscripcion", new Suscripcion());
		return "suscripcion"; // "suscripcion" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Suscripcion objSuscripcion, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
		{
			model.addAttribute("listaSuscripciones", sService.listar());
			return "suscripcion";
		}
		else {
			boolean flag = sService.grabar(objSuscripcion);
			if (flag)
				return "redirect:/suscripcion/listar";
			else {
				model.addAttribute("mensaje", "Hazlo de nuevo");
				return "redirect:/suscripcion/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Suscripcion> objSuscripcion = sService.listarId(id);
		if (objSuscripcion == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/suscripcion/listar";
		}
		else {
			model.addAttribute("listaSuscripciones", objSuscripcion);
			return "suscripcion";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				sService.eliminar(id);
				model.put("listaSuscripciones", sService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSuscripciones", sService.listar());
		}
		return "listSuscripciones";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSuscripciones", sService.listar());
		return "listSuscripciones";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Suscripcion suscripcion)
	throws ParseException
	{
		sService.listarId(suscripcion.getIdSuscripcion());
		return "listSuscripciones";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model)
	{
		model.addAttribute("suscripcion", new Suscripcion());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Suscripcion suscripcion)
	{
		List<Suscripcion> listaSuscripciones;
		
		suscripcion.setTipoSuscripcion(suscripcion.getTipoSuscripcion());
		suscripcion.setCostoSuscripcion(suscripcion.getCostoSuscripcion());
		
		if (suscripcion.getCostoSuscripcion() == 0) {
			listaSuscripciones = sService.buscarTipo(suscripcion.getTipoSuscripcion());			
		}
		else if (suscripcion.getTipoSuscripcion() == null) {
			listaSuscripciones = sService.buscarCosto(suscripcion.getCostoSuscripcion());
		}
		else {
			listaSuscripciones = sService.buscarTipoCosto(suscripcion.getTipoSuscripcion(), suscripcion.getCostoSuscripcion());
		}
		
		if (listaSuscripciones.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaSuscripciones", "listaSuscripciones");
		return "buscar";
	}
}
