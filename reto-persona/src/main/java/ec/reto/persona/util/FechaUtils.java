package ec.reto.persona.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class FechaUtils {
    public static int calcularEdad(Date fechaNacimiento) {
        //Fecha Inicial
        LocalDate fechaNac = fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault()) // Usa la zona horaria del sistema
                .toLocalDate();
        // Fecha Actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la diferencia en años
        return Period.between(fechaNac, fechaActual).getYears();
    }
}
