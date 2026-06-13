package sv.edu.ues.nomina.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.domain.Gerente;
import sv.edu.ues.nomina.domain.JefeArea;
import sv.edu.ues.nomina.domain.RolEmpleado;
import sv.edu.ues.nomina.domain.Supervisor;
import sv.edu.ues.nomina.domain.Tecnico;

class EmpleadoSeederTest {

	private final EmpleadoSeeder seeder = new EmpleadoSeeder();

	@Test
	void debeCrearVeinticincoEmpleados() {
		assertEquals(25, seeder.crearEmpleadosIniciales().size());
	}

	@Test
	void debeCrearDosGerentes() {
		long cantidad = seeder.crearEmpleadosIniciales().stream()
				.filter(Gerente.class::isInstance)
				.count();
		assertEquals(2, cantidad);
	}

	@Test
	void debeCrearTresJefesDeArea() {
		long cantidad = seeder.crearEmpleadosIniciales().stream()
				.filter(JefeArea.class::isInstance)
				.count();
		assertEquals(3, cantidad);
	}

	@Test
	void debeCrearCincoSupervisores() {
		long cantidad = seeder.crearEmpleadosIniciales().stream()
				.filter(Supervisor.class::isInstance)
				.count();
		assertEquals(5, cantidad);
	}

	@Test
	void debeCrearQuinceTecnicos() {
		long cantidad = seeder.crearEmpleadosIniciales().stream()
				.filter(Tecnico.class::isInstance)
				.count();
		assertEquals(15, cantidad);
	}

	@Test
	void debeCrearEmpleadosConRolesCorrectos() {
		long totalPorRol = seeder.crearEmpleadosIniciales().stream()
				.map(Empleado::getRol)
				.distinct()
				.count();
		assertEquals(RolEmpleado.values().length, totalPorRol);
	}

}
