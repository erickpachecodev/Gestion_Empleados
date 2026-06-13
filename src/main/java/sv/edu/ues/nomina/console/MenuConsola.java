package sv.edu.ues.nomina.console;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuConsola {

	private final List<MenuAction> acciones;
	private final ConsoleInputReader inputReader;
	private final ConsolePrinter printer;

	public MenuConsola(List<MenuAction> acciones, ConsoleInputReader inputReader, ConsolePrinter printer) {
		this.acciones = List.copyOf(Objects.requireNonNull(acciones, "acciones no puede ser null"));
		this.inputReader = Objects.requireNonNull(inputReader, "inputReader no puede ser null");
		this.printer = Objects.requireNonNull(printer, "printer no puede ser null");
	}

	public void ejecutar() {
		boolean continuar = true;
		while (continuar) {
			printer.imprimirMenu(acciones);
			String opcion = inputReader.leerTexto("Seleccione una opción: ").trim();

			if (opcion.isEmpty()) {
				printer.imprimirError("La opción no puede estar vacía.");
				continue;
			}

			Optional<MenuAction> accionSeleccionada = buscarAccion(opcion);
			if (accionSeleccionada.isEmpty()) {
				printer.imprimirError("Opción inválida. Intente nuevamente.");
				continue;
			}

			try {
				continuar = accionSeleccionada.get().ejecutar();
			} catch (RuntimeException excepcion) {
				printer.imprimirError("Ocurrió un error al ejecutar la opción: " + excepcion.getMessage());
			}
		}
	}

	private Optional<MenuAction> buscarAccion(String codigo) {
		return acciones.stream()
				.filter(accion -> accion.getCodigo().equals(codigo))
				.findFirst();
	}

}
