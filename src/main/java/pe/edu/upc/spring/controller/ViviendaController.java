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

import pe.edu.upc.spring.model.Vivienda;
import pe.edu.upc.spring.model.Propietario;


import pe.edu.upc.spring.service.IViviendaService;
import pe.edu.upc.spring.service.IPropietarioService;

@Controller
@RequestMapping("/vivienda")
public class ViviendaController {
	@Autowired
	private IPropietarioService rService;
	
	@Autowired
	private IViviendaService vService;
	
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoViviendas(Map<String, Object> model) {
		model.put("listaViviendas", vService.listar());
		return "listViviendas"; // "listViviendas" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaPropietarios", rService.listar());

		model.addAttribute("vivienda", new Vivienda());
		model.addAttribute("propietario", new Propietario());
		return "vivienda"; // "pet" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Vivienda objVivienda, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
		{
			model.addAttribute("listaPropietarios", rService.listar());
			return "vivienda";
		}
		else {
			boolean flag = vService.grabar(objVivienda);
			if (flag)
				return "redirect:/vivienda/listar";
			else {
				model.addAttribute("mensaje", "Hazlo de nuevo");
				return "redirect:/vivienda/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Vivienda> objVivienda = vService.listarId(id);
		if (objVivienda == null) {
			objRedir.addFlashAttribute("mensaje", "Hazlo de nuevo");
			return "redirect:/vivienda/listar";
		}
		else {
			model.addAttribute("listaPropietarios", rService.listar());
			if(objVivienda.isPresent())
				objVivienda.ifPresent(o->model.addAttribute("vivienda", o));
			return "vivienda";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				vService.eliminar(id);
				model.put("listaViviendas", vService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaViviendas", vService.listar());
		}
		return "listViviendas";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaViviendas", vService.listar());
		return "listViviendas";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Vivienda vivienda)
	throws ParseException
	{
		vService.listarId(vivienda.getIdVivienda());
		return "listViviendas";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model)
	{
		model.addAttribute("vivienda", new Vivienda());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Vivienda vivienda)
	{
		List<Vivienda> listaViviendas;
		vivienda.setNNombreVivienda(vivienda.getNNombreVivienda());
		listaViviendas = vService.buscarNombre(vivienda.getNNombreVivienda());
		
		if (listaViviendas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaViviendas", "listaViviendas");
		return "buscar";
		
	}
	
	
	
}