package sv.edu.ues.nomina.service.descuento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * Verifica el cálculo de la renta en los distintos tramos de la tabla.
 */
class TablaRentaTest {

	private final TablaRenta tablaRenta = new TablaRenta();

	@Test
	void debeCalcularRentaCeroParaSueldo350() {
		assertEquals(0, new BigDecimal("0.00").compareTo(tablaRenta.calcularRenta(new BigDecimal("350.00"))));
	}

	@Test
	void debeCalcularRentaParaSueldo750() {
		assertEquals(0, new BigDecimal("45.47").compareTo(tablaRenta.calcularRenta(new BigDecimal("750.00"))));
	}

	@Test
	void debeCalcularRentaParaSueldo1500() {
		assertEquals(0, new BigDecimal("180.95").compareTo(tablaRenta.calcularRenta(new BigDecimal("1500.00"))));
	}

	@Test
	void debeCalcularRentaParaSueldo5000() {
		assertEquals(0, new BigDecimal("1177.14").compareTo(tablaRenta.calcularRenta(new BigDecimal("5000.00"))));
	}

}
