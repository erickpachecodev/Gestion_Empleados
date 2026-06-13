package sv.edu.ues.nomina.console.action;

import java.util.Objects;

import sv.edu.ues.nomina.console.ConsolePrinter;
import sv.edu.ues.nomina.console.MenuAction;
import sv.edu.ues.nomina.service.NominaService;

public class OrdenarPorApellidoAction implements MenuAction {

	private final NominaService nominaService;
	private final ConsolePrinter printer;

	public OrdenarPorApellidoAction(NominaService nominaService, ConsolePrinter printer) {
		this.nominaService = Objects.requireNonNull(nominaService, "nominaService no puede ser null");
		this.printer = Objects.requireNonNull(printer, "printer no puede ser null");
	}

	@Override
	public String getCodigo() {
		return "2";
	}

	@Override
	public String getDescripcion() {
		return "Ordenar empleados por primer apellido";
	}

	@Override
	public boolean ejecutar() {
		printer.imprimirEmpleados(nominaService.ordenarPorPrimerApellido());
		return true;
	}

}
