package sv.edu.ues.nomina.service.descuento;

import java.math.BigDecimal;

public interface DescuentoStrategy {

	String getNombre();

	BigDecimal calcular(BigDecimal sueldoBase);

}
