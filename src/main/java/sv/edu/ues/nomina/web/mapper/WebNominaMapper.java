package sv.edu.ues.nomina.web.mapper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import sv.edu.ues.nomina.domain.DetalleSalario;
import sv.edu.ues.nomina.domain.Empleado;
import sv.edu.ues.nomina.domain.RolEmpleado;
import sv.edu.ues.nomina.web.viewmodel.DetalleSalarioViewModel;
import sv.edu.ues.nomina.web.viewmodel.EmpleadoResumenViewModel;
import sv.edu.ues.nomina.web.viewmodel.RolConteoViewModel;

/**
 * Transforma objetos de dominio en view models con datos formateados
 * listos para las vistas Thymeleaf.
 */
@Component
public class WebNominaMapper {

	private static final DecimalFormat FORMATO_MONEDA;

	static {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
		FORMATO_MONEDA = new DecimalFormat("$#,##0.00", simbolos);
	}

	public EmpleadoResumenViewModel toEmpleadoResumen(Empleado empleado) {
		return new EmpleadoResumenViewModel(
				empleado.getNombreCompleto(),
				empleado.getDui(),
				empleado.getSexo().getDescripcion(),
				empleado.getRol().getDescripcion(),
				formatearMoneda(empleado.getSueldoBase()));
	}

	public List<EmpleadoResumenViewModel> toEmpleadoResumenList(List<Empleado> empleados) {
		List<EmpleadoResumenViewModel> viewModels = new ArrayList<>();
		for (Empleado empleado : empleados) {
			viewModels.add(toEmpleadoResumen(empleado));
		}
		return viewModels;
	}

	public DetalleSalarioViewModel toDetalleSalario(DetalleSalario detalle) {
		Empleado empleado = detalle.getEmpleado();
		return new DetalleSalarioViewModel(
				empleado.getNombreCompleto(),
				empleado.getRol().getDescripcion(),
				formatearMoneda(detalle.getSueldoBase()),
				formatearMoneda(detalle.getIsss()),
				formatearMoneda(detalle.getAfp()),
				formatearMoneda(detalle.getRenta()),
				formatearMoneda(detalle.getTotalDescuentos()),
				formatearMoneda(detalle.getSalarioNeto()));
	}

	public List<DetalleSalarioViewModel> toDetalleSalarioList(List<DetalleSalario> detalles) {
		List<DetalleSalarioViewModel> viewModels = new ArrayList<>();
		for (DetalleSalario detalle : detalles) {
			viewModels.add(toDetalleSalario(detalle));
		}
		return viewModels;
	}

	public RolConteoViewModel toRolConteo(RolEmpleado rol, Long cantidad) {
		return new RolConteoViewModel(rol.getDescripcion(), cantidad);
	}

	public List<RolConteoViewModel> toRolConteoList(Map<RolEmpleado, Long> conteo) {
		List<RolConteoViewModel> viewModels = new ArrayList<>();
		for (RolEmpleado rol : RolEmpleado.values()) {
			viewModels.add(toRolConteo(rol, conteo.getOrDefault(rol, 0L)));
		}
		return viewModels;
	}

	public String formatearMoneda(BigDecimal valor) {
		if (valor == null) {
			return "$0.00";
		}
		return FORMATO_MONEDA.format(valor);
	}

}
