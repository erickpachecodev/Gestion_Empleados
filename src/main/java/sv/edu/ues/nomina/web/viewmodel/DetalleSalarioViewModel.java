package sv.edu.ues.nomina.web.viewmodel;

public class DetalleSalarioViewModel {

	private final String nombreCompleto;
	private final String rol;
	private final String sueldoBase;
	private final String isss;
	private final String afp;
	private final String renta;
	private final String totalDescuentos;
	private final String salarioNeto;

	public DetalleSalarioViewModel(String nombreCompleto, String rol, String sueldoBase, String isss, String afp,
			String renta, String totalDescuentos, String salarioNeto) {
		this.nombreCompleto = nombreCompleto;
		this.rol = rol;
		this.sueldoBase = sueldoBase;
		this.isss = isss;
		this.afp = afp;
		this.renta = renta;
		this.totalDescuentos = totalDescuentos;
		this.salarioNeto = salarioNeto;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getRol() {
		return rol;
	}

	public String getSueldoBase() {
		return sueldoBase;
	}

	public String getIsss() {
		return isss;
	}

	public String getAfp() {
		return afp;
	}

	public String getRenta() {
		return renta;
	}

	public String getTotalDescuentos() {
		return totalDescuentos;
	}

	public String getSalarioNeto() {
		return salarioNeto;
	}

}
