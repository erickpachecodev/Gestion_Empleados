package sv.edu.ues.nomina.repository;

import java.util.List;

import sv.edu.ues.nomina.domain.Empleado;

public interface EmpleadoRepository {

	List<Empleado> obtenerTodos();

	void agregar(Empleado empleado);

	long contar();

}
