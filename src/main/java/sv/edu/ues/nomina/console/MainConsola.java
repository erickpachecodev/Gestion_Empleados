package sv.edu.ues.nomina.console;

import java.util.List;

import sv.edu.ues.nomina.console.action.ContarPorRolAction;
import sv.edu.ues.nomina.console.action.MostrarDetalleSalariosAction;
import sv.edu.ues.nomina.console.action.MostrarEmpleadosAction;
import sv.edu.ues.nomina.console.action.OrdenarPorApellidoAction;
import sv.edu.ues.nomina.console.action.OrdenarPorSalarioAscAction;
import sv.edu.ues.nomina.console.action.OrdenarPorSalarioDescAction;
import sv.edu.ues.nomina.console.action.SalirAction;
import sv.edu.ues.nomina.factory.NominaFactory;
import sv.edu.ues.nomina.service.NominaService;

/**
 * Punto de entrada de la interfaz de consola; arma las acciones del menú
 * y lanza el ciclo de ejecución.
 */
public class MainConsola {

	public static void main(String[] args) {
		NominaService nominaService = NominaFactory.crearNominaService();
		ConsolePrinter printer = new ConsolePrinter();
		ConsoleInputReader inputReader = new ConsoleInputReader();

		List<MenuAction> acciones = List.of(
				new MostrarEmpleadosAction(nominaService, printer),
				new OrdenarPorApellidoAction(nominaService, printer),
				new OrdenarPorSalarioAscAction(nominaService, printer),
				new OrdenarPorSalarioDescAction(nominaService, printer),
				new ContarPorRolAction(nominaService, printer),
				new MostrarDetalleSalariosAction(nominaService, printer),
				new SalirAction(printer));

		MenuConsola menuConsola = new MenuConsola(acciones, inputReader, printer);
		menuConsola.ejecutar();
	}

}
