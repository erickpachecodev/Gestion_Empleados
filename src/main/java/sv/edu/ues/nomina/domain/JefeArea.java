package sv.edu.ues.nomina.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JefeArea extends Empleado {

	private static final BigDecimal SUELDO_BASE = new BigDecimal("1500.00");

	public JefeArea(String nombre, String primerApellido, String segundoApellido, String direccion,
			LocalDate fechaNacimiento, Sexo sexo, String dui) {
		super(nombre, primerApellido, segundoApellido, direccion, fechaNacimiento, sexo, dui);
	}

	@Override
	public BigDecimal getSueldoBase() {
		return SUELDO_BASE;
	}

	@Override
	public RolEmpleado getRol() {
		return RolEmpleado.JEFE_AREA;
	}

}
