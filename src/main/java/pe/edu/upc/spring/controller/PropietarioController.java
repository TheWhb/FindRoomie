package pe.edu.upc.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.service.IPropietarioService;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;

@Controller
@RequestMapping("/propietario")
public class PropietarioController {
	@Autowired
	private IPropietarioService rService;
	
	Optional<Propietario> objPropietario;
	
	int IdPropietario;
	String NombreApellido;
	private String pasadaContraseña;
	@Autowired
	private JpaUserDetailsService uService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
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
	
	@RequestMapping("/datos/{id}")
	public String CargarDatos(@PathVariable int id, Map<String, Object> model) {
		objPropietario = rService.listarId(id);
		IdPropietario = objPropietario.get().getIdPropietario();
		NombreApellido = objPropietario.get().getNPropietario() + " " + objPropietario.get().getAPropietario();
		return "redirect:/roomie/InicioR";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("propietario", new Propietario());
		return "registroP"; // "propietario" es una pagina del frontEnd para insertar y/o modificar
	}
	@RequestMapping("/registrar")
	public String registrar(@Valid Propietario obPropietario, BindingResult binRes, Model model) throws ParseException {
		
		if (binRes.hasErrors()) {
			
			if(obPropietario.getIdPropietario() > 0) {
				return "registroP";
			}
			else {
				return "propietario";
			}	
		} else {
			
			if(obPropietario.getIdPropietario() > 0) {
				if(obPropietario.getContraseñaPropietario()=="") {
					obPropietario.setContraseñaPropietario(pasadaContraseña);
				}else {
					String bcryptPassword = passwordEncoder.encode(obPropietario.getContraseñaPropietario());
					obPropietario.setContraseñaPropietario(bcryptPassword);
				}
			}
			
			else {
				String bcryptPassword = passwordEncoder.encode(obPropietario.getContraseñaPropietario());
				obPropietario.setContraseñaPropietario(bcryptPassword);	
			}
			
			Boolean flagUsers;
			Boolean flag;
			
			if(obPropietario.getIdPropietario() > 0) {
				 flagUsers = rUsers(obPropietario);
				 flag = rService.grabar(obPropietario);
			}else {
				
				Users users= uService.findByUsername(obPropietario.getEmailPropietario());
				if(users!=null) {
					model.addAttribute("mensaje", "Ya existe");
					flagUsers=false;
					flag=false;
				}else {
					 flagUsers = rUsers(obPropietario);
					 flag = rService.grabar(obPropietario);
				}

			}
			
			if (flag && flagUsers ) {
				return "redirect:/propietario/datos" + obPropietario.getIdPropietario();
			}
			else { 

				if(obPropietario.getIdPropietario() > 0) {
					return "redirect:/propietario/modificar" + obPropietario.getIdPropietario();
				}
				else {

					return "redirect:/propietario/irRegistrar";
				}	
			}
		}
	}
	public boolean  rUsers(Propietario prop) {
		Users users; 
		users= uService.findByUsername(prop.getEmailPropietario());
		if(users== null) {
			users =  new Users();
			List<Role> listRoles= new ArrayList<Role>();
			Role role= new Role();
			role.setRol("ROLE_PROP");
			listRoles.add(role);
			users.setPassword(prop.getContraseñaPropietario());
			users.setRoles(listRoles);
			users.setEnabled(true);
			users.setUsername(prop.getEmailPropietario());
		}else if(users.getPassword()!=prop.getContraseñaPropietario()){			
			users.setPassword(prop.getContraseñaPropietario());
		}
		boolean flagUsers = uService.save(users);
		return flagUsers;
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Propietario> objPropietario = rService.listarId(id);
		if (objPropietario == null) {
			objRedir.addFlashAttribute("mensaje", "No se pudo acceder");
			return "redirect:/vivienda/inicioP";
		}
		else {
			model.addAttribute("idPropietario", IdPropietario);
			model.addAttribute("NAPropietario", NombreApellido);
			if(objPropietario.isPresent())
				objPropietario.ifPresent(o->model.addAttribute("propietario", o));
			return "registroP";
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
	public String ingresarCuenta(@ModelAttribute("propietario") Propietario objPropietario, BindingResult binRes, Model model) throws ParseException {
		List<Propietario> listaPropietarios;
		
		objPropietario.setEmailPropietario(objPropietario.getEmailPropietario());
		objPropietario.setContraseñaPropietario(objPropietario.getContraseñaPropietario());
		listaPropietarios = rService.findByEmailAndPassword(objPropietario.getEmailPropietario(), objPropietario.getContraseñaPropietario());
		
		if (!listaPropietarios.isEmpty()) {
			objPropietario = listaPropietarios.get(0);
			return "redirect:/vivienda/datos/" + objPropietario.getIdPropietario();
		}
		else {
			model.addAttribute("mensaje", "Datos incorrectos");
			return "loginP";
		}
	}
}
