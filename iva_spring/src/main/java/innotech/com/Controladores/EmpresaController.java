package innotech.com.Controladores;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import innotech.com.entididades.Cliente;
import innotech.com.entididades.Empresa;
import innotech.com.paginator.PageRender;
import innotech.com.services.EmpresaServiceImp;

@Controller
@SessionAttributes("empresa")
@RequestMapping("/empresa")
public class EmpresaController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	EmpresaServiceImp empresaServ;
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String inicial (@RequestParam(name="page", defaultValue="0") int page,   Model modelo, 
			   Authentication authentication,
			   HttpServletRequest request ) {
		
		//otra forma de obtener el usuario que nos hemos autenticado
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		
		
		int elemento = Integer.parseInt(this.elementos);  
		
		if (authentication != null) {
		  logger.info(" hola '"+ authentication.getName() +"' Desde List de controlador de empresas ");
		};
		
		
		if (auth != null) {
			  logger.info(" Usando SecurityContextHolder.getContext().getAuthentication() usuario logeado->: '"+ authentication.getName() +"' Desde List de controlador de empresas ");
			};
		
		if (tieneRole("ROLE_USER")) {logger.info(" ********** SI TIENE EL ROL *******************");
		} else {logger.info(" ##### NO TIENE EL ROL ####"); }
		
		if (tieneRole2("ROLE_USER")) {logger.info(" ********** 2 FORMA SI TIENE ACCESO AL ROLL *******************");
		} else {logger.info(" ##### NO TIENE EL ROL USANDO SEGUNDA FORMA ####"); }
		
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");		
		
		if (securityContext.isUserInRole("ROLE_USER")) {
			logger.info(" Si tiene el Rol usando el SecurityContextHolderAwareRequestWrapper");
		} else{logger.info(" NO TIENES ACCESO usando el SecurityContextHolderAwareRequestWrapper");};
		
		if (request.isUserInRole("ROLE_USER")) {
			logger.info(" Si tiene el Rol usando el request.isUserInRole");
		} else{logger.info(" NO TIENES ACCESO usando el request.isUserInRole");};
		
		
		Pageable  pageRequest =  PageRequest.of(page, elemento);
		Page<Empresa> empresas = empresaServ.findAll(pageRequest);
		
		PageRender<Empresa> pageRender = new PageRender<>("/empresa/listar", empresas, elemento) ;
				
		modelo.addAttribute("mensaje","hola desde thymeleaf");		
		modelo.addAttribute("titulo","Mantenimiento de Empresa");
		//modelo.addAttribute("datos",empresaServ.findAll());		
		modelo.addAttribute("datos",empresas);
		modelo.addAttribute("page",pageRender);
		return "/empresa/listar";
	};
	
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Empresa empresa = empresaServ.findById(id);
		if (empresa==null) {
			flash.addAttribute("error", "La empresa no existe en la Base de datos");
			return "redirect:/empresa/listar";
		}
		//
		model.put("empresa", empresa);
		model.put("titulo", "Detalle Empresa: "+empresa.getNombre());
		model.put("datos",empresa);
		//
		return "/empresa/ver";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/form") 
	public String form (Model modelo) {	
		Empresa empresa = new Empresa();
		//empresa.setNombre("Carlitos Avalos");
		//---
		modelo.addAttribute("titulo","Creación de Empresa");	
		modelo.addAttribute("empresa",empresa);
		
		return "/empresa/form";
	};
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="empresa") Empresa empresa, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Empresa");						
			return "/empresa/form";
		} else {
		
			String mensajeFlash = (empresa.getId() != null)? "Empresa Editada con éxito" : " Empresa guardada con éxito "  ;
		     empresaServ.save(empresa);
			model.addAttribute("titulo","Creación de Empresa");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/empresa/listar";
		}
	};
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Empresa empresa = null;
		
		if(id > 0) {
			empresa = empresaServ.findById(id);
			if (empresa == null) {
				flash.addFlashAttribute("error", " La empresa no existe en la Base de datos");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", " Empresa no existe");
			return "redirect:/listar";
		}
		model.put("empresa", empresa);
		model.put("titulo", "Editar Empresa");
		flash.addFlashAttribute("success", " Empresa guardada con éxito");
		return "/empresa/form";
	}
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			empresaServ.delete(id);
		}
		flash.addFlashAttribute("success", " Empresa eliminada con éxito");
		return "redirect:/empresa/listar";
	}
	
	
	private boolean tieneRole(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		// Si es nulo no tiene acceso  y retornar falso
		if (context == null) {
			return false;
		}
			
		Authentication auth = context.getAuthentication();
		
		// Si la autenticacion esta vacia retornar falso
		if ( auth ==null ) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		for( GrantedAuthority authority : authorities) {
			if (role.equals(authority.getAuthority())) {
				logger.info("Rol encontrado "+authority.getAuthority());
				return true;
				
			}
		}
		
		return false;
	};
	
	
  private boolean tieneRole2(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		// Si es nulo no tiene acceso  y retornar falso
		if (context == null) {
			return false;
		}
			
		Authentication auth = context.getAuthentication();
		
		// Si la autenticacion esta vacia retornar falso
		if ( auth ==null ) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		return authorities.contains(new SimpleGrantedAuthority(role));
		
		/*for( GrantedAuthority authority : authorities) {
			if (role.equals(authority.getAuthority())) {
				logger.info("Rol encontrado "+authority.getAuthority());
				return true;
				
			}
		}		
		return false;*/
	};
	
}
