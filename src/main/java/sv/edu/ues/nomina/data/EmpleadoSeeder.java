package sv.edu.ues.nomina.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.domain.Gerente;
import sv.edu.ues.nomina.domain.JefeArea;
import sv.edu.ues.nomina.domain.Sexo;
import sv.edu.ues.nomina.domain.Supervisor;
import sv.edu.ues.nomina.domain.Tecnico;

/**
 * Carga la nómina inicial de empleados requerida por el sistema.
 */
public class EmpleadoSeeder {

	/**
	 * Crea la lista de empleados iniciales con sus datos predefinidos.
	 */
	public List<Empleado> crearEmpleadosIniciales() {
		Empleado[] empleados = {
				new Gerente("Carlos", "Aguilar", "Mendoza", "Colonia Escalón, San Salvador",
						LocalDate.of(1975, 3, 12), Sexo.MASCULINO, "01234567-8"),
				new Gerente("María", "Zelaya", "Romero", "Residencial Los Próceres, San Salvador",
						LocalDate.of(1980, 7, 25), Sexo.FEMENINO, "02345678-9"),

				new JefeArea("Roberto", "Alfaro", "Pineda", "Colonia San Benito, San Salvador",
						LocalDate.of(1982, 1, 18), Sexo.MASCULINO, "03456789-0"),
				new JefeArea("Lucía", "Castillo", "Navarro", "Antiguo Cuscatlán, La Libertad",
						LocalDate.of(1985, 9, 5), Sexo.FEMENINO, "04567890-1"),
				new JefeArea("Fernando", "Díaz", "Salazar", "Santa Tecla, La Libertad",
						LocalDate.of(1978, 11, 30), Sexo.MASCULINO, "05678901-2"),

				new Supervisor("Ana", "Barrera", "López", "Colonia Miramonte, San Salvador",
						LocalDate.of(1988, 4, 14), Sexo.FEMENINO, "06789012-3"),
				new Supervisor("José", "Cáceres", "Vega", "Soyapango, San Salvador",
						LocalDate.of(1987, 6, 22), Sexo.MASCULINO, "07890123-4"),
				new Supervisor("Patricia", "Escobar", "Ruiz", "Mejicanos, San Salvador",
						LocalDate.of(1990, 2, 8), Sexo.FEMENINO, "08901234-5"),
				new Supervisor("Miguel", "Flores", "Campos", "Apopa, San Salvador",
						LocalDate.of(1986, 12, 1), Sexo.MASCULINO, "09012345-6"),
				new Supervisor("Sandra", "García", "Mejía", "Ilopango, San Salvador",
						LocalDate.of(1991, 8, 17), Sexo.FEMENINO, "00123456-7"),

				new Tecnico("Pedro", "Araya", "Herrera", "Colonia Médica, San Salvador",
						LocalDate.of(1995, 5, 3), Sexo.MASCULINO, "01234987-6"),
				new Tecnico("Laura", "Benítez", "Morales", "Santa Ana, Santa Ana",
						LocalDate.of(1996, 10, 19), Sexo.FEMENINO, "02345987-5"),
				new Tecnico("Diego", "Callejas", "Peña", "San Miguel, San Miguel",
						LocalDate.of(1994, 3, 27), Sexo.MASCULINO, "03456987-4"),
				new Tecnico("Gabriela", "Domínguez", "Rivas", "Sonsonate, Sonsonate",
						LocalDate.of(1997, 7, 11), Sexo.FEMENINO, "04567987-3"),
				new Tecnico("Ricardo", "Espinoza", "Torres", "Usulután, Usulután",
						LocalDate.of(1993, 1, 9), Sexo.MASCULINO, "05678987-2"),
				new Tecnico("Claudia", "Fuentes", "Aguirre", "Ahuachapán, Ahuachapán",
						LocalDate.of(1998, 9, 23), Sexo.FEMENINO, "06789987-1"),
				new Tecnico("Andrés", "González", "Martínez", "Chalatenango, Chalatenango",
						LocalDate.of(1992, 11, 15), Sexo.MASCULINO, "07890987-0"),
				new Tecnico("Verónica", "Hernández", "Silva", "La Unión, La Unión",
						LocalDate.of(1999, 4, 6), Sexo.FEMENINO, "08901987-9"),
				new Tecnico("Oscar", "Jiménez", "Cruz", "San Vicente, San Vicente",
						LocalDate.of(1991, 8, 28), Sexo.MASCULINO, "09012987-8"),
				new Tecnico("Natalia", "López", "Reyes", "Cuscatlán, Cuscatlán",
						LocalDate.of(2000, 2, 14), Sexo.FEMENINO, "00123987-7"),
				new Tecnico("Héctor", "Molina", "Castro", "La Paz, La Paz",
						LocalDate.of(1990, 6, 7), Sexo.MASCULINO, "01234987-6"),
				new Tecnico("Isabel", "Navarro", "Delgado", "Morazán, Morazán",
						LocalDate.of(1998, 12, 20), Sexo.FEMENINO, "02345987-5"),
				new Tecnico("Raúl", "Ortega", "Vásquez", "San Martín, San Salvador",
						LocalDate.of(1993, 5, 31), Sexo.MASCULINO, "03456987-4"),
				new Tecnico("Daniela", "Pérez", "Sánchez", "Ciudad Delgado, San Salvador",
						LocalDate.of(1997, 3, 2), Sexo.FEMENINO, "04567987-3"),
				new Tecnico("Eduardo", "Quintanilla", "Ramírez", "Colonia Layco, San Salvador",
						LocalDate.of(1994, 10, 10), Sexo.MASCULINO, "05678987-2")
		};

		return new ArrayList<>(Arrays.asList(empleados));
	}

}
