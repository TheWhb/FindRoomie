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

import pe.edu.upc.spring.model.Roomie;
import pe.edu.upc.spring.model.SuscripcionXRoomie;
import pe.edu.upc.spring.model.Vivienda;
import pe.edu.upc.spring.service.IRoomieService;
import pe.edu.upc.spring.service.ISuscripcionXRoomieService;
import pe.edu.upc.spring.service.IViviendaService;

@Controller
@RequestMapping("/roomie")
public class RoomieController {
	@Autowired
	private IRoomieService rService;
	
	@Autowired
	private IViviendaService vService;
	
	@Autowired
	private ISuscripcionXRoomieService srService;
	
	Optional<Roomie> objRoomie;
	int IdRoomie;
	String NombreApellido;
	String Correo;
	String Presentacion;
	Boolean Premiun;
	Vivienda ViviendaAlquilada;
	int IdSuscripcion;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoRoomies(Map<String, Object> model) {
		model.put("listaRoomies", rService.listar());
		return "listRoomies"; // "listRoomies" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irLogin")
	public String irPaginaLogin(Model model) {
		model.addAttribute("roomie", new Roomie());
		return "loginR";
	}
	
	@RequestMapping("/datos/{id}")
	public String CargarDatos(@PathVariable int id, Map<String, Object> model) {
		objRoomie = rService.listarId(id);
		IdRoomie = objRoomie.get().getIdRoomie();
		NombreApellido = objRoomie.get().getNRoomie() + " " + objRoomie.get().getARoomie();
		ViviendaAlquilada = objRoomie.get().getViviendaRoomie();
		Correo = objRoomie.get().getEmailRoomie();
		Presentacion = "DNI: " + objRoomie.get().getDNIRoomie() + " Celular: " + objRoomie.get().getNroCelularRoomie();
		List<SuscripcionXRoomie> lista = srService.listar();
		for (SuscripcionXRoomie SXR : lista) {
			if (SXR.getRoomieSuscripcionXRoomie().getIdRoomie() == id) {
				Premiun = true;
				IdSuscripcion = SXR.getIdSuscripcionXRoomie();
				return "redirect:/roomie/InicioR";
			}
		}
		Premiun = false;
		return "redirect:/roomie/InicioR";
	}
	
	@RequestMapping("/InicioR")
	public String irPaginaListadoViviendas(Map<String, Object> model) {
		model.put("idRoomie", IdRoomie);
		model.put("NombreApellido", NombreApellido);
		model.put("Correo", Correo);
		model.put("Presentacion", Presentacion);
		model.put("Premiun", Premiun);
		if	(Premiun)
			model.put("mensajePremiun", "Tiene Premiun");
		else
			model.put("mensajePremiun", "No tiene Premiun");
		model.put("idSuscripcion", IdSuscripcion);
		model.put("ViviendaAlquilada", ViviendaAlquilada);
		model.put("listaViviendas", vService.listar());
		return "inicioR";
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
			objRoomie.setViviendaRoomie(ViviendaAlquilada);
			boolean flag = rService.grabar(objRoomie);
			if (flag) 
				return "redirect:/roomie/datos/" + objRoomie.getIdRoomie();
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
			return "redirect:/roomie/inicioR";
		}
		else {
			model.addAttribute("idRoomie", IdRoomie);
			model.addAttribute("NombreApellido", NombreApellido);
			if(objRoomie.isPresent())
				objRoomie.ifPresent(o->model.addAttribute("roomie", o));
			return "registroR";
		}
	}
	
	@RequestMapping("/alquilar")
	public String alquilar(Map<String, Object> model,  @RequestParam(value="id") Integer id) 
		throws ParseException
	{
		Optional<Roomie> roomie = rService.listarId(IdRoomie);
		try {
			if (roomie.get() != null || (id !=null && id>0)) {
				Vivienda vivienda = vService.buscarId(id).get();
				roomie.get().setViviendaRoomie(vivienda);
				boolean flag = rService.grabar(roomie.get());
				if (flag) {
					ViviendaAlquilada = vivienda;
					model.put("mensaje", "¡Alquilado!");
				}
				else {
					model.put("mensaje", "No se pudo alquilar");
				}
			}
			else {
				model.put("mensaje", "No se pudo alquilar");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo alquilar");
			model.put("listaViviendas", vService.listar());
		}
		model.put("idRoomie", IdRoomie);
		model.put("NombreApellido", NombreApellido);
		model.put("Correo", Correo);
		model.put("Presentacion", Presentacion);
		model.put("Premiun", Premiun);
		if	(Premiun)
			model.put("mensajePremiun", "Tiene Premiun");
		else
			model.put("mensajePremiun", "No tiene Premiun");
		model.put("idSuscripcion", IdSuscripcion);
		model.put("ViviendaAlquilada", ViviendaAlquilada);
		return "inicioR";
	}
	
	@RequestMapping("/cancelar")
	public String cancelar(Map<String, Object> model,  @RequestParam(value="id") Integer id) 
		throws ParseException
	{
		Optional<Roomie> roomie = rService.listarId(IdRoomie);
		try {
			if (roomie.get() != null || (id !=null && id>0)) {
				roomie.get().setViviendaRoomie(null);
				boolean flag = rService.grabar(roomie.get());
				if (flag) {
					ViviendaAlquilada = null;
					model.put("mensaje", "¡Cancelado!");
				}
				else {
					model.put("mensaje", "No se pudo cancelar");
				}
			}
			else {
				model.put("mensaje", "No se pudo cancelar");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo cancelar");
			model.put("ViviendaAlquilada", ViviendaAlquilada);
		}
		model.put("idRoomie", IdRoomie);
		model.put("NombreApellido", NombreApellido);
		model.put("Correo", Correo);
		model.put("Presentacion", Presentacion);
		model.put("Premiun", Premiun);
		if	(Premiun)
			model.put("mensajePremiun", "Tiene Premiun");
		else
			model.put("mensajePremiun", "No tiene Premiun");
		model.put("idSuscripcion", IdSuscripcion);
		model.put("listaViviendas", vService.listar());
		return "inicioR";
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
	
	@RequestMapping("/validarUsuario")
	public String ingresarCuenta(@ModelAttribute("roomie") Roomie objRoomie, BindingResult binRes, Model model) throws ParseException {
		List<Roomie> listaRoomies;
		objRoomie.setEmailRoomie(objRoomie.getEmailRoomie());
		objRoomie.setContraseñaRoomie(objRoomie.getContraseñaRoomie());
		listaRoomies = rService.findByEmailAndPassword(objRoomie.getEmailRoomie(), objRoomie.getContraseñaRoomie());
    
		if (!listaRoomies.isEmpty()) {
			objRoomie = listaRoomies.get(0);
			return "redirect:/roomie/datos/" + objRoomie.getIdRoomie();
		}
		else {
			model.addAttribute("mensaje", "Datos incorrectos");
			return "loginR";
		}
	}
}
