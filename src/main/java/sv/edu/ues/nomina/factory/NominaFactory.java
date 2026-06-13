package sv.edu.ues.nomina.factory;

import java.util.List;

import sv.edu.ues.nomina.data.EmpleadoSeeder;
import sv.edu.ues.nomina.repository.EmpleadoMemoriaRepository;
import sv.edu.ues.nomina.repository.EmpleadoRepository;
import sv.edu.ues.nomina.service.CalculadoraSalario;
import sv.edu.ues.nomina.service.NominaService;
import sv.edu.ues.nomina.service.descuento.AfpDescuentoStrategy;
import sv.edu.ues.nomina.service.descuento.DescuentoStrategy;
import sv.edu.ues.nomina.service.descuento.IsssDescuentoStrategy;
import sv.edu.ues.nomina.service.descuento.RentaDescuentoStrategy;

/**
 * Ensambla las dependencias del sistema de nómina sin acoplarse a la capa
 * de presentación (web o consola).
 */
public class NominaFactory {

	private NominaFactory() {
	}

	public static NominaService crearNominaService() {
		EmpleadoSeeder seeder = new EmpleadoSeeder();
		EmpleadoRepository empleadoRepository = new EmpleadoMemoriaRepository(seeder.crearEmpleadosIniciales());
		CalculadoraSalario calculadoraSalario = crearCalculadoraSalario();
		return new NominaService(empleadoRepository, calculadoraSalario);
	}

	public static CalculadoraSalario crearCalculadoraSalario() {
		List<DescuentoStrategy> descuentos = List.of(
				new IsssDescuentoStrategy(),
				new AfpDescuentoStrategy(),
				new RentaDescuentoStrategy());
		return new CalculadoraSalario(descuentos);
	}

}
