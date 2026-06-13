package sv.edu.ues.nomina.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sv.edu.ues.nomina.service.NominaService;
import sv.edu.ues.nomina.web.mapper.WebNominaMapper;

@Controller
public class HomeController {

	private final NominaService nominaService;
	private final WebNominaMapper mapper;

	public HomeController(NominaService nominaService, WebNominaMapper mapper) {
		this.nominaService = nominaService;
		this.mapper = mapper;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("totalEmpleados", nominaService.obtenerTodos().size());
		model.addAttribute("conteoRoles", mapper.toRolConteoList(nominaService.contarEmpleadosPorRol()));
		return "index";
	}

}
