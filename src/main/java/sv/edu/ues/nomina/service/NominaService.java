package sv.edu.ues.nomina.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.domain.RolEmpleado;
import sv.edu.ues.nomina.repository.EmpleadoRepository;

public class NominaService {

	private final EmpleadoRepository empleadoRepository;
	private final CalculadoraSalario calculadoraSalario;

	public NominaService(EmpleadoRepository empleadoRepository, CalculadoraSalario calculadoraSalario) {
		this.empleadoRepository = Objects.requireNonNull(empleadoRepository, "empleadoRepository no puede ser null");
		this.calculadoraSalario = Objects.requireNonNull(calculadoraSalario, "calculadoraSalario no puede ser null");
	}

	public List<Empleado> obtenerTodos() {
		return empleadoRepository.obtenerTodos();
	}

	public List<Empleado> ordenarPorPrimerApellido() {
		return empleadoRepository.obtenerTodos().stream()
				.sorted(Comparator
						.comparing(Empleado::getPrimerApellido, String.CASE_INSENSITIVE_ORDER)
						.thenComparing(Empleado::getSegundoApellido, String.CASE_INSENSITIVE_ORDER)
						.thenComparing(Empleado::getNombre, String.CASE_INSENSITIVE_ORDER))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Empleado> ordenarPorSalarioNetoAscendente() {
		return empleadoRepository.obtenerTodos().stream()
				.sorted(Comparator.comparing(empleado -> calculadoraSalario.calcularDetalleSalario(empleado).getSalarioNeto()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Empleado> ordenarPorSalarioNetoDescendente() {
		return empleadoRepository.obtenerTodos().stream()
				.sorted(Comparator
						.<Empleado, java.math.BigDecimal>comparing(
								empleado -> calculadoraSalario.calcularDetalleSalario(empleado).getSalarioNeto())
						.reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public Map<RolEmpleado, Long> contarEmpleadosPorRol() {
		Map<RolEmpleado, Long> conteo = new EnumMap<>(RolEmpleado.class);
		for (RolEmpleado rol : RolEmpleado.values()) {
			conteo.put(rol, 0L);
		}
		empleadoRepository.obtenerTodos().stream()
				.collect(Collectors.groupingBy(Empleado::getRol, Collectors.counting()))
				.forEach(conteo::put);
		return conteo;
	}

	public DetalleSalario calcularDetalleSalario(Empleado empleado) {
		return calculadoraSalario.calcularDetalleSalario(empleado);
	}

	public List<DetalleSalario> obtenerDetalleSalarios() {
		return empleadoRepository.obtenerTodos().stream()
				.map(calculadoraSalario::calcularDetalleSalario)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}
