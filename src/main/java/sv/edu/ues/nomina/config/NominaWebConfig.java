package sv.edu.ues.nomina.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sv.edu.ues.nomina.factory.NominaFactory;
import sv.edu.ues.nomina.service.NominaService;

@Configuration
public class NominaWebConfig {

	@Bean
	public NominaService nominaService() {
		return NominaFactory.crearNominaService();
	}

}
