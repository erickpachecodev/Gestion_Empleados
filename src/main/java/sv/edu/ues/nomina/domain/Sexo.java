package sv.edu.ues.nomina.domain;

/**
 * Sexo del empleado.
 */
public enum Sexo {

	MASCULINO("Masculino"),
	FEMENINO("Femenino");

	private final String descripcion;

	Sexo(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
