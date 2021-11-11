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

import pe.edu.upc.spring.model.SuscripcionXRoomie;
import pe.edu.upc.spring.model.Suscripcion;
import pe.edu.upc.spring.model.Roomie;
import pe.edu.upc.spring.service.ISuscripcionXRoomieService;
import pe.edu.upc.spring.service.ISuscripcionService;
import pe.edu.upc.spring.service.IRoomieService;

@Controller
@RequestMapping("/SuscripcionXRoomie/")
public class SuscripcionXRoomieController {
	@Autowired
	private ISuscripcionXRoomieService srService;
	
	@Autowired
	private ISuscripcionService sService;
	
	@Autowired
	private IRoomieService rService;
	
	Optional<Roomie> objRoomie;
	int IdRoomie;
	String NombreApellido;
	
	@RequestMapping("/datos/{id}")
	public String CargarDatos(@PathVariable int id, Map<String, Object> model) {
		objRoomie = rService.listarId(id);
		IdRoomie = objRoomie.get().getIdRoomie();
		NombreApellido = objRoomie.get().getNRoomie() + " " + objRoomie.get().getARoomie();
		return "redirect:/SuscripcionXRoomie/";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPublicacionRoomies(Map<String, Object> model) {
		return "pSuscripcionR";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("idRoomie", IdRoomie);
		model.addAttribute("NARoomie", NombreApellido);
		model.addAttribute("listaSuscripciones", sService.listar());
		
		model.addAttribute("suscripcionXRoomie", new SuscripcionXRoomie());
		model.addAttribute("suscripcion", new Suscripcion());
		model.addAttribute("roomie", new Roomie());
		return "registrarSuscripcionR";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute SuscripcionXRoomie objSuscripcionXRoomie, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "registrarSuscripcionR";
		else {
			boolean flag = srService.grabar(objSuscripcionXRoomie);
			if (flag)
				return "redirect:/roomie/datos/" + objSuscripcionXRoomie.getRoomieSuscripcionXRoomie().getIdRoomie();
			else {
				model.addAttribute("mensaje", "No se pudo acceder");
				return "redirect:/SuscripcionXRoomie/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<SuscripcionXRoomie> objSuscripcionXRoomie = srService.listarId(id);
		if (objSuscripcionXRoomie == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/planSuscripcionRoomie/listar";
		}
		else {
			model.addAttribute("suscripcionXRoomie", objSuscripcionXRoomie);
			return "planSuscripcionRoomie";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id, @RequestParam(value="Roo") Integer Roo) {
		try {
			if (id!=null && id>0) {
				srService.eliminar(id);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
		}
		return "redirect:/roomie/datos/" + Roo;
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaSuscripcionXRoomies", srService.listar());
		return "listPlanSuscripcionRoomies";
	}
	
}
