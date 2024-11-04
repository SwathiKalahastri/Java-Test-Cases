package customerQueries;
/* 
 * This test was created by Swathi Kalahastri
 * for Date Conversion
 */

import java.time.*;
import java.time.format.*;

public class DateConversion {
    public static void main(String[] args) {
        String inputDate = "2024.298";

        LocalDate parsedDate = LocalDate.ofYearDay(Integer.parseInt(inputDate.split("\\.")[0]), Integer.parseInt(inputDate.split("\\.")[1]));
        System.out.println(parsedDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = parsedDate.format(formatter);

        System.out.println(formattedDate);
    }
}