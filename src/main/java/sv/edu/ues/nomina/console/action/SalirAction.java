package sv.edu.ues.nomina.console.action;

import java.util.Objects;

import sv.edu.ues.nomina.console.ConsolePrinter;
import sv.edu.ues.nomina.console.MenuAction;

public class SalirAction implements MenuAction {

	private final ConsolePrinter printer;

	public SalirAction(ConsolePrinter printer) {
		this.printer = Objects.requireNonNull(printer, "printer no puede ser null");
	}

	@Override
	public String getCodigo() {
		return "0";
	}

	@Override
	public String getDescripcion() {
		return "Salir";
	}

	@Override
	public boolean ejecutar() {
		printer.imprimirMensaje("Gracias por usar el Sistema de Nómina. ¡Hasta pronto!");
		return false;
	}

}
