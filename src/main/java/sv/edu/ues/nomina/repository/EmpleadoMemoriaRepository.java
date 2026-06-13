package sv.edu.ues.nomina.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import sv.edu.ues.nomina.domain.Empleado;

public class EmpleadoMemoriaRepository implements EmpleadoRepository {

	private final List<Empleado> empleados;

	public EmpleadoMemoriaRepository(List<Empleado> empleados) {
		Objects.requireNonNull(empleados, "empleados no puede ser null");
		this.empleados = new ArrayList<>(empleados);
	}

	@Override
	public List<Empleado> obtenerTodos() {
		return Collections.unmodifiableList(empleados);
	}

	@Override
	public void agregar(Empleado empleado) {
		Objects.requireNonNull(empleado, "empleado no puede ser null");
		empleados.add(empleado);
	}

	@Override
	public long contar() {
		return empleados.size();
	}

}
