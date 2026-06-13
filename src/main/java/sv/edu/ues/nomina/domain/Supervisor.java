package sv.edu.ues.nomina.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Empleado con rol de supervisor y sueldo base fijo.
 */
public class Supervisor extends Empleado {

	private static final BigDecimal SUELDO_BASE = new BigDecimal("750.00");

	public Supervisor(String nombre, String primerApellido, String segundoApellido, String direccion,
			LocalDate fechaNacimiento, Sexo sexo, String dui) {
		super(nombre, primerApellido, segundoApellido, direccion, fechaNacimiento, sexo, dui);
	}

	@Override
	public BigDecimal getSueldoBase() {
		return SUELDO_BASE;
	}

	@Override
	public RolEmpleado getRol() {
		return RolEmpleado.SUPERVISOR;
	}

}
