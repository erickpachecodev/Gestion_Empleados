package sv.edu.ues.nomina.service.descuento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class AfpDescuentoStrategyTest {

	private final AfpDescuentoStrategy strategy = new AfpDescuentoStrategy();

	@Test
	void debeCalcularAfpParaSueldoMil() {
		BigDecimal resultado = strategy.calcular(new BigDecimal("1000.00"));
		assertEquals(0, new BigDecimal("77.50").compareTo(resultado));
	}

}
