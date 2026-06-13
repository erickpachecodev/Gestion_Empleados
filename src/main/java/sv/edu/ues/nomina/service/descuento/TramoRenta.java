package sv.edu.ues.nomina.service.descuento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class TramoRenta {

	private final BigDecimal desde;
	private final BigDecimal hasta;
	private final BigDecimal porcentaje;
	private final BigDecimal sobreExceso;
	private final BigDecimal cuotaFija;

	public TramoRenta(BigDecimal desde, BigDecimal hasta, BigDecimal porcentaje, BigDecimal sobreExceso,
			BigDecimal cuotaFija) {
		this.desde = Objects.requireNonNull(desde, "desde no puede ser null");
		this.hasta = hasta;
		this.porcentaje = Objects.requireNonNull(porcentaje, "porcentaje no puede ser null");
		this.sobreExceso = Objects.requireNonNull(sobreExceso, "sobreExceso no puede ser null");
		this.cuotaFija = Objects.requireNonNull(cuotaFija, "cuotaFija no puede ser null");
	}

	public BigDecimal getDesde() {
		return desde;
	}

	public BigDecimal getHasta() {
		return hasta;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public BigDecimal getSobreExceso() {
		return sobreExceso;
	}

	public BigDecimal getCuotaFija() {
		return cuotaFija;
	}

	public boolean aplica(BigDecimal sueldoBase) {
		Objects.requireNonNull(sueldoBase, "sueldoBase no puede ser null");
		boolean cumpleMinimo = sueldoBase.compareTo(desde) >= 0;
		boolean cumpleMaximo = hasta == null || sueldoBase.compareTo(hasta) <= 0;
		return cumpleMinimo && cumpleMaximo;
	}

	public BigDecimal calcular(BigDecimal sueldoBase) {
		Objects.requireNonNull(sueldoBase, "sueldoBase no puede ser null");
		if (porcentaje.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}
		BigDecimal renta = sueldoBase.subtract(sobreExceso)
				.multiply(porcentaje)
				.add(cuotaFija);
		return renta.setScale(2, RoundingMode.HALF_UP);
	}

}
