package sv.edu.ues.nomina.console.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import sv.edu.ues.nomina.console.ConsolePrinter;
import sv.edu.ues.nomina.console.MenuAction;
import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.service.NominaService;

/**
 * Acción de menú que muestra los empleados ordenados por salario neto ascendente.
 */
public class OrdenarPorSalarioAscAction implements MenuAction {

	private final NominaService nominaService;
	private final ConsolePrinter printer;

	public OrdenarPorSalarioAscAction(NominaService nominaService, ConsolePrinter printer) {
		this.nominaService = Objects.requireNonNull(nominaService, "nominaService no puede ser null");
		this.printer = Objects.requireNonNull(printer, "printer no puede ser null");
	}

	@Override
	public String getCodigo() {
		return "3";
	}

	@Override
	public String getDescripcion() {
		return "Ordenar empleados por salario neto ascendente";
	}

	@Override
	public boolean ejecutar() {
		List<DetalleSalario> detalles = new ArrayList<>();
		for (Empleado empleado : nominaService.ordenarPorSalarioNetoAscendente()) {
			detalles.add(nominaService.calcularDetalleSalario(empleado));
		}
		printer.imprimirEmpleadosConSalarioNeto(detalles);
		return true;
	}

}
