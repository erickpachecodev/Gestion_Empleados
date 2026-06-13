package sv.edu.ues.nomina.domain;

/**
 * Roles posibles de un empleado dentro de la nómina.
 */
public enum RolEmpleado {

	GERENTE("Gerente"),
	TECNICO("Técnico"),
	JEFE_AREA("Jefe de área"),
	SUPERVISOR("Supervisor");

	private final String descripcion;

	RolEmpleado(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
