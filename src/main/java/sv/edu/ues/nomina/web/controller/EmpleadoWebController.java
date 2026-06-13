package sv.edu.ues.nomina.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.service.NominaService;
import sv.edu.ues.nomina.web.mapper.WebNominaMapper;
import sv.edu.ues.nomina.web.viewmodel.DetalleSalarioViewModel;

/**
 * Controlador de las vistas de empleados. Solo coordina la presentación web
 * y delega la lógica en {@link NominaService}.
 */
@Controller
@RequestMapping("/empleados")
public class EmpleadoWebController {

	private final NominaService nominaService;
	private final WebNominaMapper mapper;

	public EmpleadoWebController(NominaService nominaService, WebNominaMapper mapper) {
		this.nominaService = nominaService;
		this.mapper = mapper;
	}

	@GetMapping
	public String listarTodos(Model model) {
		model.addAttribute("titulo", "Todos los empleados");
		model.addAttribute("empleados", mapper.toEmpleadoResumenList(nominaService.obtenerTodos()));
		return "empleados/lista";
	}

	@GetMapping("/apellidos")
	public String listarPorApellido(Model model) {
		model.addAttribute("titulo", "Empleados ordenados por primer apellido");
		model.addAttribute("empleados", mapper.toEmpleadoResumenList(nominaService.ordenarPorPrimerApellido()));
		return "empleados/lista";
	}

	@GetMapping("/salarios/asc")
	public String listarSalariosAscendente(Model model) {
		model.addAttribute("titulo", "Empleados por salario neto ascendente");
		model.addAttribute("detalles", toDetallesOrdenados(nominaService.ordenarPorSalarioNetoAscendente()));
		return "empleados/salarios";
	}

	@GetMapping("/salarios/desc")
	public String listarSalariosDescendente(Model model) {
		model.addAttribute("titulo", "Empleados por salario neto descendente");
		model.addAttribute("detalles", toDetallesOrdenados(nominaService.ordenarPorSalarioNetoDescendente()));
		return "empleados/salarios";
	}

	@GetMapping("/roles")
	public String listarConteoPorRol(Model model) {
		model.addAttribute("titulo", "Cantidad de empleados por rol");
		model.addAttribute("conteoRoles", mapper.toRolConteoList(nominaService.contarEmpleadosPorRol()));
		model.addAttribute("totalEmpleados", nominaService.obtenerTodos().size());
		return "empleados/roles";
	}

	@GetMapping("/detalles-salarios")
	public String listarDetalleSalarios(Model model) {
		model.addAttribute("titulo", "Detalle salarial completo");
		model.addAttribute("detalles", mapper.toDetalleSalarioList(nominaService.obtenerDetalleSalarios()));
		return "empleados/detalles-salarios";
	}

	private List<DetalleSalarioViewModel> toDetallesOrdenados(List<Empleado> empleados) {
		List<DetalleSalario> detalles = new ArrayList<>();
		for (Empleado empleado : empleados) {
			detalles.add(nominaService.calcularDetalleSalario(empleado));
		}
		return mapper.toDetalleSalarioList(detalles);
	}

}
