package sv.edu.ues.nomina.console.action;

import java.util.Objects;

import sv.edu.ues.nomina.console.ConsolePrinter;
import sv.edu.ues.nomina.console.MenuAction;
import sv.edu.ues.nomina.service.NominaService;

/**
 * Acción de menú que muestra el detalle salarial completo de cada empleado.
 */
public class MostrarDetalleSalariosAction implements MenuAction {

	private final NominaService nominaService;
	private final ConsolePrinter printer;

	public MostrarDetalleSalariosAction(NominaService nominaService, ConsolePrinter printer) {
		this.nominaService = Objects.requireNonNull(nominaService, "nominaService no puede ser null");
		this.printer = Objects.requireNonNull(printer, "printer no puede ser null");
	}

	@Override
	public String getCodigo() {
		return "6";
	}

	@Override
	public String getDescripcion() {
		return "Mostrar detalle salarial completo";
	}

	@Override
	public boolean ejecutar() {
		printer.imprimirDetalleSalarios(nominaService.obtenerDetalleSalarios());
		return true;
	}

}
