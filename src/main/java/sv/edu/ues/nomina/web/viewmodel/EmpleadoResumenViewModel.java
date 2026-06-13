package sv.edu.ues.nomina.web.viewmodel;

public class EmpleadoResumenViewModel {

	private final String nombreCompleto;
	private final String dui;
	private final String sexo;
	private final String rol;
	private final String sueldoBase;

	public EmpleadoResumenViewModel(String nombreCompleto, String dui, String sexo, String rol, String sueldoBase) {
		this.nombreCompleto = nombreCompleto;
		this.dui = dui;
		this.sexo = sexo;
		this.rol = rol;
		this.sueldoBase = sueldoBase;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getDui() {
		return dui;
	}

	public String getSexo() {
		return sexo;
	}

	public String getRol() {
		return rol;
	}

	public String getSueldoBase() {
		return sueldoBase;
	}

}
