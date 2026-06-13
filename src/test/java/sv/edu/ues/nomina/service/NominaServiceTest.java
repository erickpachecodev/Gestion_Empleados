package sv.edu.ues.nomina.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.domain.RolEmpleado;
import sv.edu.ues.nomina.factory.NominaFactory;

/**
 * Verifica las operaciones de nómina: consulta, conteo por rol y ordenamientos.
 */
class NominaServiceTest {

	private NominaService nominaService;

	@BeforeEach
	void setUp() {
		nominaService = NominaFactory.crearNominaService();
	}

	@Test
	void obtenerTodosDebeDevolverVeinticincoEmpleados() {
		assertEquals(25, nominaService.obtenerTodos().size());
	}

	@Test
	void contarEmpleadosPorRolDebeDevolverCantidadesCorrectas() {
		Map<RolEmpleado, Long> conteo = nominaService.contarEmpleadosPorRol();

		assertEquals(2L, conteo.get(RolEmpleado.GERENTE));
		assertEquals(15L, conteo.get(RolEmpleado.TECNICO));
		assertEquals(3L, conteo.get(RolEmpleado.JEFE_AREA));
		assertEquals(5L, conteo.get(RolEmpleado.SUPERVISOR));
	}

	@Test
	void ordenarPorPrimerApellidoDebeOrdenarAlfabeticamente() {
		List<Empleado> ordenados = nominaService.ordenarPorPrimerApellido();

		assertTrue(esListaOrdenadaPorApellido(ordenados));
		assertEquals("Aguilar", ordenados.get(0).getPrimerApellido());
		assertEquals("Zelaya", ordenados.get(ordenados.size() - 1).getPrimerApellido());
	}

	@Test
	void ordenarPorSalarioNetoAscendenteDebeOrdenarDeMenorAMayor() {
		List<Empleado> ordenados = nominaService.ordenarPorSalarioNetoAscendente();

		assertTrue(esListaOrdenadaPorSalarioNetoAscendente(ordenados));
		assertEquals(RolEmpleado.TECNICO, ordenados.get(0).getRol());
		assertEquals(RolEmpleado.GERENTE, ordenados.get(ordenados.size() - 1).getRol());
	}

	@Test
	void ordenarPorSalarioNetoDescendenteDebeOrdenarDeMayorAMenor() {
		List<Empleado> ordenados = nominaService.ordenarPorSalarioNetoDescendente();

		assertTrue(esListaOrdenadaPorSalarioNetoDescendente(ordenados));
		assertEquals(RolEmpleado.GERENTE, ordenados.get(0).getRol());
		assertEquals(RolEmpleado.TECNICO, ordenados.get(ordenados.size() - 1).getRol());
	}

	@Test
	void obtenerDetalleSalariosDebeDevolverVeinticincoDetalles() {
		List<DetalleSalario> detalles = nominaService.obtenerDetalleSalarios();
		assertEquals(25, detalles.size());
	}

	private boolean esListaOrdenadaPorApellido(List<Empleado> empleados) {
		Comparator<Empleado> comparador = Comparator
				.comparing(Empleado::getPrimerApellido, String.CASE_INSENSITIVE_ORDER)
				.thenComparing(Empleado::getSegundoApellido, String.CASE_INSENSITIVE_ORDER)
				.thenComparing(Empleado::getNombre, String.CASE_INSENSITIVE_ORDER);

		for (int i = 0; i < empleados.size() - 1; i++) {
			if (comparador.compare(empleados.get(i), empleados.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}

	private boolean esListaOrdenadaPorSalarioNetoAscendente(List<Empleado> empleados) {
		for (int i = 0; i < empleados.size() - 1; i++) {
			BigDecimal actual = nominaService.calcularDetalleSalario(empleados.get(i)).getSalarioNeto();
			BigDecimal siguiente = nominaService.calcularDetalleSalario(empleados.get(i + 1)).getSalarioNeto();
			if (actual.compareTo(siguiente) > 0) {
				return false;
			}
		}
		return true;
	}

	private boolean esListaOrdenadaPorSalarioNetoDescendente(List<Empleado> empleados) {
		for (int i = 0; i < empleados.size() - 1; i++) {
			BigDecimal actual = nominaService.calcularDetalleSalario(empleados.get(i)).getSalarioNeto();
			BigDecimal siguiente = nominaService.calcularDetalleSalario(empleados.get(i + 1)).getSalarioNeto();
			if (actual.compareTo(siguiente) < 0) {
				return false;
			}
		}
		return true;
	}

}
