package sv.edu.ues.nomina.console;

import java.util.Scanner;

/**
 * Lee la entrada del usuario desde la consola.
 */
public class ConsoleInputReader {

	private final Scanner scanner;

	public ConsoleInputReader() {
		this.scanner = new Scanner(System.in);
	}

	public String leerTexto(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextLine();
	}

}
