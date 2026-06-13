package sv.edu.ues.nomina.service.descuento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class RentaDescuentoStrategy implements DescuentoStrategy {

	private static final int ESCALA_MONETARIA = 2;
	private final TablaRenta tablaRenta;

	public RentaDescuentoStrategy() {
		this(new TablaRenta());
	}

	public RentaDescuentoStrategy(TablaRenta tablaRenta) {
		this.tablaRenta = Objects.requireNonNull(tablaRenta, "tablaRenta no puede ser null");
	}

	@Override
	public String getNombre() {
		return "RENTA";
	}

	@Override
	public BigDecimal calcular(BigDecimal sueldoBase) {
		Objects.requireNonNull(sueldoBase, "sueldoBase no puede ser null");
		return tablaRenta.calcularRenta(sueldoBase).setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
	}

}
