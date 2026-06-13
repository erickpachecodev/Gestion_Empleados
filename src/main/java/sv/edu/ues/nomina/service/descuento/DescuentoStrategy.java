package sv.edu.ues.nomina.service.descuento;

import java.math.BigDecimal;

/**
 * Define el contrato para aplicar un descuento sobre el sueldo base.
 * Aplica el patrón Strategy: cada descuento es una estrategia intercambiable.
 */
public interface DescuentoStrategy {

	String getNombre();

	BigDecimal calcular(BigDecimal sueldoBase);

}
