package sv.edu.ues.nomina.service.descuento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Estrategia de descuento del ISSS, calculado como un porcentaje fijo del sueldo base.
 */
public class IsssDescuentoStrategy implements DescuentoStrategy {

	private static final BigDecimal PORCENTAJE = new BigDecimal("0.075");
	private static final int ESCALA_MONETARIA = 2;

	@Override
	public String getNombre() {
		return "ISSS";
	}

	@Override
	public BigDecimal calcular(BigDecimal sueldoBase) {
		Objects.requireNonNull(sueldoBase, "sueldoBase no puede ser null");
		return sueldoBase.multiply(PORCENTAJE).setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
	}

}
