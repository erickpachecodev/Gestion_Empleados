package sv.edu.ues.nomina.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class DetalleDescuento {

	private final String nombre;
	private final BigDecimal monto;

	public DetalleDescuento(String nombre, BigDecimal monto) {
		this.nombre = Objects.requireNonNull(nombre, "nombre no puede ser null");
		this.monto = Objects.requireNonNull(monto, "monto no puede ser null");
	}

	public String getNombre() {
		return nombre;
	}

	public BigDecimal getMonto() {
		return monto;
	}

}
