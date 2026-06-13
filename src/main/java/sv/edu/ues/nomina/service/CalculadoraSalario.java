package sv.edu.ues.nomina.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import sv.edu.ues.nomina.domain.DetalleDescuento;
import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.service.descuento.DescuentoStrategy;

/**
 * Calcula el detalle salarial de un empleado aplicando las estrategias
 * de descuento configuradas.
 */
public class CalculadoraSalario {

	private static final int ESCALA_MONETARIA = 2;
	private final List<DescuentoStrategy> descuentos;

	public CalculadoraSalario(List<DescuentoStrategy> descuentos) {
		Objects.requireNonNull(descuentos, "descuentos no puede ser null");
		if (descuentos.isEmpty()) {
			throw new IllegalArgumentException("descuentos no puede estar vacía");
		}
		this.descuentos = List.copyOf(descuentos);
	}

	/**
	 * Calcula el detalle salarial completo de un empleado.
	 */
	public DetalleSalario calcularDetalleSalario(Empleado empleado) {
		Objects.requireNonNull(empleado, "empleado no puede ser null");

		BigDecimal sueldoBase = empleado.getSueldoBase();
		List<DetalleDescuento> detalleDescuentos = new ArrayList<>();
		BigDecimal totalDescuentos = BigDecimal.ZERO;

		for (DescuentoStrategy estrategia : descuentos) {
			BigDecimal montoDescuento = estrategia.calcular(sueldoBase);
			detalleDescuentos.add(new DetalleDescuento(estrategia.getNombre(), montoDescuento));
			totalDescuentos = totalDescuentos.add(montoDescuento);
		}

		totalDescuentos = totalDescuentos.setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
		BigDecimal salarioNeto = sueldoBase.subtract(totalDescuentos)
				.setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);

		return new DetalleSalario(empleado, sueldoBase, detalleDescuentos, totalDescuentos, salarioNeto);
	}

}
