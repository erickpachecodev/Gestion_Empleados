package sv.edu.ues.nomina.console;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ConsoleMoneyFormatter {

	private static final DecimalFormat FORMATO;

	static {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
		FORMATO = new DecimalFormat("$#,##0.00", simbolos);
	}

	private ConsoleMoneyFormatter() {
	}

	public static String formatear(BigDecimal monto) {
		if (monto == null) {
			return "$0.00";
		}
		return FORMATO.format(monto);
	}

}
