package sv.edu.ues.nomina.console;

import java.util.List;
import java.util.Map;

import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.domain.RolEmpleado;

public class ConsolePrinter {

	public void imprimirTitulo(String titulo) {
		System.out.println();
		System.out.println("========================================");
		System.out.printf("      %s%n", titulo);
		System.out.println("========================================");
	}

	public void imprimirMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	public void imprimirError(String mensaje) {
		System.out.println("[ERROR] " + mensaje);
	}

	public void imprimirMenu(List<MenuAction> acciones) {
		imprimirTitulo("SISTEMA DE NOMINA - CONSOLA");
		for (MenuAction accion : acciones) {
			System.out.printf("%s. %s%n", accion.getCodigo(), accion.getDescripcion());
		}
		System.out.println("========================================");
	}

	public void imprimirEmpleados(List<Empleado> empleados) {
		if (empleados.isEmpty()) {
			imprimirMensaje("No hay empleados registrados.");
			return;
		}

		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.printf("%-4s%-32s%-14s%-12s%-16s%-14s%n",
				"#", "Nombre completo", "DUI", "Sexo", "Rol", "Sueldo base");
		System.out.println("---------------------------------------------------------------------------------------------------------");

		int indice = 1;
		for (Empleado empleado : empleados) {
			System.out.printf("%-4d%-32s%-14s%-12s%-16s%-14s%n",
					indice++,
					truncar(empleado.getNombreCompleto(), 30),
					empleado.getDui(),
					empleado.getSexo().getDescripcion(),
					empleado.getRol().getDescripcion(),
					ConsoleMoneyFormatter.formatear(empleado.getSueldoBase()));
		}
		System.out.println();
	}

	public void imprimirEmpleadosConSalarioNeto(List<DetalleSalario> detalles) {
		if (detalles.isEmpty()) {
			imprimirMensaje("No hay empleados registrados.");
			return;
		}

		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.printf("%-4s%-32s%-16s%-14s%-14s%n",
				"#", "Nombre completo", "Rol", "Sueldo base", "Salario neto");
		System.out.println("---------------------------------------------------------------------------------------------------------");

		int indice = 1;
		for (DetalleSalario detalle : detalles) {
			Empleado empleado = detalle.getEmpleado();
			System.out.printf("%-4d%-32s%-16s%-14s%-14s%n",
					indice++,
					truncar(empleado.getNombreCompleto(), 30),
					empleado.getRol().getDescripcion(),
					ConsoleMoneyFormatter.formatear(detalle.getSueldoBase()),
					ConsoleMoneyFormatter.formatear(detalle.getSalarioNeto()));
		}
		System.out.println();
	}

	public void imprimirDetalleSalarios(List<DetalleSalario> detalles) {
		if (detalles.isEmpty()) {
			imprimirMensaje("No hay detalles salariales disponibles.");
			return;
		}

		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-4s%-26s%-16s%-14s%-11s%-11s%-11s%-14s%-14s%n",
				"#", "Empleado", "Rol", "Sueldo base", "ISSS", "AFP", "Renta", "Total desc.", "Salario neto");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		int indice = 1;
		for (DetalleSalario detalle : detalles) {
			Empleado empleado = detalle.getEmpleado();
			System.out.printf("%-4d%-26s%-16s%-14s%-11s%-11s%-11s%-14s%-14s%n",
					indice++,
					truncar(empleado.getNombreCompleto(), 24),
					empleado.getRol().getDescripcion(),
					ConsoleMoneyFormatter.formatear(detalle.getSueldoBase()),
					ConsoleMoneyFormatter.formatear(detalle.getIsss()),
					ConsoleMoneyFormatter.formatear(detalle.getAfp()),
					ConsoleMoneyFormatter.formatear(detalle.getRenta()),
					ConsoleMoneyFormatter.formatear(detalle.getTotalDescuentos()),
					ConsoleMoneyFormatter.formatear(detalle.getSalarioNeto()));
		}
		System.out.println();
	}

	public void imprimirConteoPorRol(Map<RolEmpleado, Long> conteo) {
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.printf("%-16s%s%n", "Rol", "Cantidad");
		System.out.println("-------------------------------------");

		long total = 0;
		for (RolEmpleado rol : RolEmpleado.values()) {
			long cantidad = conteo.getOrDefault(rol, 0L);
			System.out.printf("%-16s%d%n", rol.getDescripcion(), cantidad);
			total += cantidad;
		}

		System.out.println("-------------------------------------");
		System.out.printf("%-16s%d%n", "Total", total);
		System.out.println();
	}

	private String truncar(String texto, int longitudMaxima) {
		if (texto.length() <= longitudMaxima) {
			return texto;
		}
		return texto.substring(0, longitudMaxima - 2) + "..";
	}

}
