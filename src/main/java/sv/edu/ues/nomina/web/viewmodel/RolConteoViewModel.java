package sv.edu.ues.nomina.web.viewmodel;

/**
 * Cantidad de empleados por rol, formateada para mostrarse en Thymeleaf.
 */
public class RolConteoViewModel {

	private final String rol;
	private final Long cantidad;

	public RolConteoViewModel(String rol, Long cantidad) {
		this.rol = rol;
		this.cantidad = cantidad;
	}

	public String getRol() {
		return rol;
	}

	public Long getCantidad() {
		return cantidad;
	}

}
