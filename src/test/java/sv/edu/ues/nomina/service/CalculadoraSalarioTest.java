package sv.edu.ues.nomina.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Gerente;
import sv.edu.ues.nomina.domain.JefeArea;
import sv.edu.ues.nomina.domain.Sexo;
import sv.edu.ues.nomina.domain.Supervisor;
import sv.edu.ues.nomina.domain.Tecnico;
import sv.edu.ues.nomina.factory.NominaFactory;
import sv.edu.ues.nomina.service.descuento.AfpDescuentoStrategy;
import sv.edu.ues.nomina.service.descuento.IsssDescuentoStrategy;
import sv.edu.ues.nomina.service.descuento.RentaDescuentoStrategy;

class CalculadoraSalarioTest {

	private CalculadoraSalario calculadoraSalario;

	@BeforeEach
	void setUp() {
		calculadoraSalario = NominaFactory.crearCalculadoraSalario();
	}

	@Test
	void debeCalcularDetalleParaTecnico() {
		Tecnico tecnico = crearTecnico();
		DetalleSalario detalle = calculadoraSalario.calcularDetalleSalario(tecnico);

		assertEquals(0, new BigDecimal("350.00").compareTo(detalle.getSueldoBase()));
		assertEquals(0, new BigDecimal("26.25").compareTo(detalle.getIsss()));
		assertEquals(0, new BigDecimal("27.13").compareTo(detalle.getAfp()));
		assertEquals(0, new BigDecimal("0.00").compareTo(detalle.getRenta()));
		assertEquals(0, new BigDecimal("296.62").compareTo(detalle.getSalarioNeto()));
	}

	@Test
	void debeCalcularDetalleParaSupervisor() {
		Supervisor supervisor = crearSupervisor();
		DetalleSalario detalle = calculadoraSalario.calcularDetalleSalario(supervisor);

		assertEquals(0, new BigDecimal("750.00").compareTo(detalle.getSueldoBase()));
		assertEquals(0, new BigDecimal("56.25").compareTo(detalle.getIsss()));
		assertEquals(0, new BigDecimal("58.13").compareTo(detalle.getAfp()));
		assertEquals(0, new BigDecimal("45.47").compareTo(detalle.getRenta()));
		assertEquals(0, new BigDecimal("590.15").compareTo(detalle.getSalarioNeto()));
	}

	@Test
	void debeCalcularDetalleParaJefeArea() {
		JefeArea jefeArea = crearJefeArea();
		DetalleSalario detalle = calculadoraSalario.calcularDetalleSalario(jefeArea);

		assertEquals(0, new BigDecimal("1500.00").compareTo(detalle.getSueldoBase()));
		assertEquals(0, new BigDecimal("112.50").compareTo(detalle.getIsss()));
		assertEquals(0, new BigDecimal("116.25").compareTo(detalle.getAfp()));
		assertEquals(0, new BigDecimal("180.95").compareTo(detalle.getRenta()));
		assertEquals(0, new BigDecimal("1090.30").compareTo(detalle.getSalarioNeto()));
	}

	@Test
	void debeCalcularDetalleParaGerente() {
		Gerente gerente = crearGerente();
		DetalleSalario detalle = calculadoraSalario.calcularDetalleSalario(gerente);

		assertEquals(0, new BigDecimal("5000.00").compareTo(detalle.getSueldoBase()));
		assertEquals(0, new BigDecimal("375.00").compareTo(detalle.getIsss()));
		assertEquals(0, new BigDecimal("387.50").compareTo(detalle.getAfp()));
		assertEquals(0, new BigDecimal("1177.14").compareTo(detalle.getRenta()));
		assertEquals(0, new BigDecimal("3060.36").compareTo(detalle.getSalarioNeto()));
	}

	@Test
	void debeUsarEstrategiasConfiguradas() {
		CalculadoraSalario calculadora = new CalculadoraSalario(List.of(
				new IsssDescuentoStrategy(),
				new AfpDescuentoStrategy(),
				new RentaDescuentoStrategy()));

		DetalleSalario detalle = calculadora.calcularDetalleSalario(crearTecnico());
		assertEquals(3, detalle.getDescuentos().size());
	}

	private Tecnico crearTecnico() {
		return new Tecnico("Pedro", "Araya", "Herrera", "San Salvador",
				LocalDate.of(1995, 5, 3), Sexo.MASCULINO, "01234987-6");
	}

	private Supervisor crearSupervisor() {
		return new Supervisor("Ana", "Barrera", "López", "San Salvador",
				LocalDate.of(1988, 4, 14), Sexo.FEMENINO, "06789012-3");
	}

	private JefeArea crearJefeArea() {
		return new JefeArea("Roberto", "Alfaro", "Pineda", "San Salvador",
				LocalDate.of(1982, 1, 18), Sexo.MASCULINO, "03456789-0");
	}

	private Gerente crearGerente() {
		return new Gerente("Carlos", "Aguilar", "Mendoza", "San Salvador",
				LocalDate.of(1975, 3, 12), Sexo.MASCULINO, "01234567-8");
	}

}
