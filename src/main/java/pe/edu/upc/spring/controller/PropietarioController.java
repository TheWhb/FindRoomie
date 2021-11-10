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

import pe.edu.upc.spring.model.Propietario;
import pe.edu.upc.spring.service.IPropietarioService;

@Controller
@RequestMapping("/propietario")
public class PropietarioController {
	@Autowired
	private IPropietarioService rService;
	
	private Propietario sesionPropietario;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPropietarios(Map<String, Object> model) {
		model.put("listaPropietarios", rService.listar());
		return "listPropietarios"; // "listPropietarios" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irLogin")
	public String irPaginaLogin(Model model) {
		model.addAttribute("propietario", new Propietario());
		return "loginP";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("propietario", new Propietario());
		return "registroP"; // "propietario" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Propietario objPropietario, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "registroP";
		else {
			boolean flag = rService.grabar(objPropietario);
			if (flag) {
				sesionPropietario = objPropietario;
				model.addAttribute("mensaje", objPropietario.getNPropietario());
				return "redirect:/vivienda/datos/" + objPropietario.getIdPropietario();
			}
			else {
				model.addAttribute("mensaje", "No se pudo acceder");
				return "redirect:/propietario/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Propietario> objPropietario = rService.listarId(id);
		if (objPropietario == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/propietario/listar";
		}
		else {
			model.addAttribute("propietario", objPropietario);
			return "propietario";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaPropietarios", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPropietarios", rService.listar());
		}
		return "listPropietarios";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaPropietarios", rService.listar());
		return "listPropietarios";
	}
	
	@RequestMapping("/validarUsuario")
	public String ingresarCuenta(@ModelAttribute("propietario") Propietario objPropietario, BindingResult binRes) throws ParseException {
		List<Propietario> listaPropietarios;
		objPropietario.setEmailPropietario(objPropietario.getEmailPropietario());
		objPropietario.setContraseniaPropietario(objPropietario.getContraseniaPropietario());
		listaPropietarios = rService.findByEmailAndPassword(objPropietario.getEmailPropietario(), objPropietario.getContraseniaPropietario());

	
		if (!listaPropietarios.isEmpty()) {
			objPropietario = listaPropietarios.get(0);
			sesionPropietario = objPropietario;
			return "redirect:/vivienda/datos/" + objPropietario.getIdPropietario();
		}
		else 
			return "loginP";
	}
	
}
