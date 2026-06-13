package sv.edu.ues.nomina.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sv.edu.ues.nomina.factory.NominaFactory;
import sv.edu.ues.nomina.service.NominaService;

/**
 * Configuración de Spring que expone el {@link NominaService} como bean para la capa web.
 */
@Configuration
public class NominaWebConfig {

	@Bean
	public NominaService nominaService() {
		return NominaFactory.crearNominaService();
	}

}
