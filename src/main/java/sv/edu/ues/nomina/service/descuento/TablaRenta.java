package sv.edu.ues.nomina.service.descuento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * Implementa los tramos de renta definidos en el enunciado y calcula
 * el impuesto correspondiente a un sueldo base.
 */
public class TablaRenta {

	private static final int ESCALA_MONETARIA = 2;
	private final List<TramoRenta> tramos;

	public TablaRenta() {
		this.tramos = List.of(
				new TramoRenta(
						new BigDecimal("0.01"),
						new BigDecimal("472.00"),
						new BigDecimal("0.00"),
						new BigDecimal("0.00"),
						new BigDecimal("0.00")),
				new TramoRenta(
						new BigDecimal("472.01"),
						new BigDecimal("895.24"),
						new BigDecimal("0.10"),
						new BigDecimal("472.00"),
						new BigDecimal("17.67")),
				new TramoRenta(
						new BigDecimal("895.25"),
						new BigDecimal("2038.10"),
						new BigDecimal("0.20"),
						new BigDecimal("895.24"),
						new BigDecimal("60.00")),
				new TramoRenta(
						new BigDecimal("2038.11"),
						null,
						new BigDecimal("0.30"),
						new BigDecimal("2038.10"),
						new BigDecimal("288.57")));
	}

	/**
	 * Calcula la renta de un sueldo base ubicando el tramo que le corresponde.
	 */
	public BigDecimal calcularRenta(BigDecimal sueldoBase) {
		Objects.requireNonNull(sueldoBase, "sueldoBase no puede ser null");
		if (sueldoBase.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("sueldoBase no puede ser negativo");
		}

		return tramos.stream()
				.filter(tramo -> tramo.aplica(sueldoBase))
				.findFirst()
				.map(tramo -> tramo.calcular(sueldoBase))
				.orElse(BigDecimal.ZERO.setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP));
	}

}
