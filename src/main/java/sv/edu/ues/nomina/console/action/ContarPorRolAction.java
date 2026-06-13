package sv.edu.ues.nomina.console.action;

import java.util.Objects;

import sv.edu.ues.nomina.console.ConsolePrinter;
import sv.edu.ues.nomina.console.MenuAction;
import sv.edu.ues.nomina.service.NominaService;

/**
 * Acción de menú que muestra la cantidad de empleados por rol.
 */
public class ContarPorRolAction implements MenuAction {

	private final NominaService nominaService;
	private final ConsolePrinter printer;

	public ContarPorRolAction(NominaService nominaService, ConsolePrinter printer) {
		this.nominaService = Objects.requireNonNull(nominaService, "nominaService no puede ser null");
		this.printer = Objects.requireNonNull(printer, "printer no puede ser null");
	}

	@Override
	public String getCodigo() {
		return "5";
	}

	@Override
	public String getDescripcion() {
		return "Mostrar cantidad de empleados por rol";
	}

	@Override
	public boolean ejecutar() {
		printer.imprimirConteoPorRol(nominaService.contarEmpleadosPorRol());
		return true;
	}

}
