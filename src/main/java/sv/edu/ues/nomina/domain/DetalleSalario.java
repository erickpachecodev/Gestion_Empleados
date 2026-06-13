package sv.edu.ues.nomina.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa el resultado final del cálculo salarial de un empleado:
 * sueldo base, descuentos aplicados, total descontado y salario neto.
 */
public class DetalleSalario {

	private final Empleado empleado;
	private final BigDecimal sueldoBase;
	private final List<DetalleDescuento> descuentos;
	private final BigDecimal totalDescuentos;
	private final BigDecimal salarioNeto;

	public DetalleSalario(Empleado empleado, BigDecimal sueldoBase, List<DetalleDescuento> descuentos,
			BigDecimal totalDescuentos, BigDecimal salarioNeto) {
		this.empleado = Objects.requireNonNull(empleado, "empleado no puede ser null");
		this.sueldoBase = Objects.requireNonNull(sueldoBase, "sueldoBase no puede ser null");
		this.descuentos = Collections.unmodifiableList(
				Objects.requireNonNull(descuentos, "descuentos no puede ser null"));
		this.totalDescuentos = Objects.requireNonNull(totalDescuentos, "totalDescuentos no puede ser null");
		this.salarioNeto = Objects.requireNonNull(salarioNeto, "salarioNeto no puede ser null");
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public BigDecimal getSueldoBase() {
		return sueldoBase;
	}

	public List<DetalleDescuento> getDescuentos() {
		return descuentos;
	}

	public BigDecimal getTotalDescuentos() {
		return totalDescuentos;
	}

	public BigDecimal getSalarioNeto() {
		return salarioNeto;
	}

	public BigDecimal getIsss() {
		return buscarDescuento("ISSS");
	}

	public BigDecimal getAfp() {
		return buscarDescuento("AFP");
	}

	public BigDecimal getRenta() {
		return buscarDescuento("RENTA");
	}

	// Busca el monto de un descuento por nombre; retorna cero si no existe.
	private BigDecimal buscarDescuento(String nombre) {
		return descuentos.stream()
				.filter(descuento -> descuento.getNombre().equals(nombre))
				.map(DetalleDescuento::getMonto)
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

}
