package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PricingSchemeUI {
    public static void displayHodidayList(ArrayList<LocalDate> holidayList) {
        System.out.println("\nHoliday list:");
        for (int i = 0; i < holidayList.size(); i++) {
            System.out.println((i + 1 ) + ". " + holidayList.get(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }
}
