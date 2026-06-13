package sv.edu.ues.nomina.console;

/**
 * Contrato de una opción del menú de consola. Aplica una variante del
 * patrón Command: cada opción encapsula su propia acción ejecutable.
 */
public interface MenuAction {

	String getCodigo();

	String getDescripcion();

	boolean ejecutar();

}
