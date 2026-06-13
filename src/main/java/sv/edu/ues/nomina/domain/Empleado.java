package sv.edu.ues.nomina.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Empleado {

	private final String nombre;
	private final String primerApellido;
	private final String segundoApellido;
	private final String direccion;
	private final LocalDate fechaNacimiento;
	private final Sexo sexo;
	private final String dui;

	protected Empleado(String nombre, String primerApellido, String segundoApellido, String direccion,
			LocalDate fechaNacimiento, Sexo sexo, String dui) {
		this.nombre = validarTexto(nombre, "nombre");
		this.primerApellido = validarTexto(primerApellido, "primerApellido");
		this.segundoApellido = validarTexto(segundoApellido, "segundoApellido");
		this.direccion = validarTexto(direccion, "direccion");
		this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, "fechaNacimiento no puede ser null");
		this.sexo = Objects.requireNonNull(sexo, "sexo no puede ser null");
		this.dui = validarTexto(dui, "dui");
	}

	public abstract BigDecimal getSueldoBase();

	public abstract RolEmpleado getRol();

	public String getNombreCompleto() {
		return nombre + " " + primerApellido + " " + segundoApellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getDui() {
		return dui;
	}

	private static String validarTexto(String valor, String campo) {
		Objects.requireNonNull(valor, campo + " no puede ser null");
		String valorTrim = valor.trim();
		if (valorTrim.isEmpty()) {
			throw new IllegalArgumentException(campo + " no puede estar vacío");
		}
		return valorTrim;
	}

}
