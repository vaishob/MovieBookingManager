package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Booking;
import entity.Seat;
import entity.SeatStatus;
import entity.SeatType;
import entity.ShowTime;

public class BookingUI {
    public static final HashMap<Integer, String> ROW_MAP = new HashMap<Integer, String>() {
        {
            put(0, "A");
            put(1, "B");
            put(2, "C");
            put(3, "D");
            put(4, "E");
            put(5, "F");
            put(6, "G");
            put(7, "H");
            put(8, "I");
            put(9, "K");
        }
    };

    public static final HashMap<String, Integer> REVERSE_ROW_MAP = new HashMap<String, Integer>() {
        {
            put("A", 0);
            put("B", 1);
            put("C", 2);
            put("D", 3);
            put("E", 4);
            put("F", 5);
            put("G", 6);
            put("H", 7);
            put("I", 8);
            put("K", 9);
        }
    };

    public static void displayLayOut(Seat[][] layout) {

        System.out.println("-------------------- Screen --------------------\n\n");
        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            if (i != 8) {
                System.out.print((i + 1) + "   ");
            } else {
                System.out.print((i + 1) + "  ");
            }
            if (i == 4) {
                System.out.print("\t   ");
            }
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(ROW_MAP.get(i));
            for (int j = 0; j < 10; j++) {
                if (layout[i][j].getSeatStatus().getLabel().equalsIgnoreCase(SeatStatus.TAKEN.getLabel())) {
                    if (layout[i][j].getSeatType().getLabel().equalsIgnoreCase(SeatType.NORMAL.getLabel())) {
                        System.out.print(" [X]");
                    } else {
                        System.out.print(" <X>");
                    }
                } else {
                    if (layout[i][j].getSeatType().getLabel().equalsIgnoreCase(SeatType.NORMAL.getLabel())) {
                        System.out.print(" [ ]");
                    } else {
                        System.out.print(" < >");
                    }
                }
                if (j == 4) {
                    System.out.print("\t ");
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("------------------- Entrance -------------------\n");

        System.out.println("[ ]: Normal seat available\t< >: Premium seat available");
        System.out.println("[X]: Normal seat taken    \t<X>: Premium seat taken");

        System.out.println();

    }

    public static void displayBookingConfirmInfo(Booking booking) {
        System.out.println("Booking information:");
        System.out.println("Name: " + booking.getMovieGoer().getName());
        System.out.println("Mobile Number: " + booking.getMovieGoer().getMobile());
        System.out.println("Email: " + booking.getMovieGoer().getEmail());
        System.out.println("Cineplex: " + booking.getCineplexName());
        System.out.println("Cinema Code: " + booking.getShowTime().getCinemaCode());
        System.out.println("Movie: " + booking.getShowTime().getMovie().getTitle());
        System.out.println("Time: "
                + booking.getShowTime().getStartDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.print("Seat(s): ");
        for (int i = 0; i < booking.tickets().size(); i++) {
            System.out.print(
                    ROW_MAP.get(booking.tickets().get(i).getRow()) + (booking.tickets().get(i).getColumn() + 1) + " ");
        }
        System.out.println("Transaction ID: " + booking.getTransactionId());
        System.out.println("Price: " + booking.getPrice());
        System.out.println();
    }

    public static void displayBookingHistory(ArrayList<Booking> bookingList) {
        System.out.println("\nBooking history:\n");
        for (int i = bookingList.size() - 1; i >= 0; i--) {
            Booking booking = bookingList.get(i);
            System.out.println("Cineplex: " + booking.getCineplexName());
            System.out.println("Cinema Code: " + booking.getShowTime().getCinemaCode());
            System.out.println("Movie: " + booking.getShowTime().getMovie().getTitle());
            System.out.println("Time: "
                    + booking.getShowTime().getStartDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            System.out.print("Seat(s): ");
            for (int j = 0; j < booking.tickets().size(); j++) {
                System.out.print(ROW_MAP.get(booking.tickets().get(j).getRow())
                        + (booking.tickets().get(j).getColumn() + 1) + " ");
            }
            System.out.println();
            System.out.println("Transaction ID: " + booking.getTransactionId());
            System.out.println("Price: " + booking.getPrice());
            System.out.println("---------------------------------------\n");
        }
    }

}
