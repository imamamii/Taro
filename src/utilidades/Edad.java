package utilidades;

import java.time.LocalDate;
import java.time.Period;

public class Edad {

	public static String calcularEdad(LocalDate cumple) {
		LocalDate now = LocalDate.now(); // gets localDate
		String edad = null;
		if(cumple!=null) {
			Period diff = Period.between(cumple, now); // difference between the dates is calculated
			edad = String.valueOf(diff.getYears());
		}
		return edad;
	}
}
