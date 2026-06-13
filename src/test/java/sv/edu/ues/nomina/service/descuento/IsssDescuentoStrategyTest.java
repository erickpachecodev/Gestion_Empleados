package sv.edu.ues.nomina.service.descuento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class IsssDescuentoStrategyTest {

	private final IsssDescuentoStrategy strategy = new IsssDescuentoStrategy();

	@Test
	void debeCalcularIsssParaSueldoMil() {
		BigDecimal resultado = strategy.calcular(new BigDecimal("1000.00"));
		assertEquals(0, new BigDecimal("75.00").compareTo(resultado));
	}

}
