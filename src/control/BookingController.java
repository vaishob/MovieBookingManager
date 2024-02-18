package control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import boundary.BookingUI;
import boundary.CommonUI;
import boundary.ShowTimeUI;
import entity.AgeGroup;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.DataPath;
import entity.Movie;
import entity.MovieGoer;
import entity.PricingScheme;
import entity.Seat;
import entity.SeatStatus;
import entity.Serialization;
import entity.ShowTime;
import entity.ShowingStatus;
import entity.Ticket;

public class BookingController {
    public static void bookingTicket(MovieGoer movieGoer) {
        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);

        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to book!\n");
            return;
        }

        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (choice < 1 || choice > cineplexList.size()) {
            ShowTimeUI.displayCineplexList(cineplexList);
            CommonUI.displaySingleMessage("Enter your choice:");
            choice = input.nextInt();
        }

        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to book!\n");
            return;
        }

        int cineplexIndex = choice - 1;
        Cineplex cineplex = cineplexList.get(cineplexIndex);

        choice = 0;
        while (choice < 1 || choice > cineplexList.size()) {
            ShowTimeUI.displayCinemaList(cineplex);
            CommonUI.displaySingleMessage("Enter your choice:");
            choice = input.nextInt();
        }

        int cinemaIndex = choice - 1;
        Cinema cinema = cineplex.getCinemas().get(cinemaIndex);

        ArrayList<ShowTime> showTimeList = cinema.getShowTimes();

        for (int l = 0; l < showTimeList.size(); l++) {
            ShowTime showTime = showTimeList.get(l);
            if (showTime.getMovie().getShowingStatus().getLabel().equalsIgnoreCase(ShowingStatus.COMING_SOON.getLabel())
                    || showTime.getMovie().getShowingStatus().getLabel()
                            .equalsIgnoreCase(ShowingStatus.END_OF_SHOWING.getLabel())) {
                showTimeList.remove(l);
            }
        }

        if (showTimeList == null || showTimeList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no show time to book!\n");
            return;
        }

        choice = 0;
        while (choice < 1 || choice > showTimeList.size()) {
            ShowTimeUI.displayShowTimeList(showTimeList);
            CommonUI.displaySingleMessage("Enter your choice:");
            choice = input.nextInt();
        }

        int showTimeIndex = choice - 1;
        ShowTime showTime = showTimeList.get(showTimeIndex);
        Seat[][] layout = showTime.getLayout();
        BookingUI.displayLayOut(layout);

        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        Double price = 0.0;
        Integer noTicket = getNoTicket();
        PricingScheme pricingScheme = (PricingScheme) Serialization.readSerializedObject(DataPath.PRCING_SCHEME);

        for (int i = 0; i < noTicket; i++) {
            while (true) {
                CommonUI.displaySingleMessage("Choose seat for ticket " + (i + 1));
                Integer row = getRow();
                Integer column = getColumn();

                if (layout[row][column].getSeatStatus().getLabel().equalsIgnoreCase(SeatStatus.NOT_TAKEN.getLabel())) {
                    Ticket ticket = new Ticket(row, column);
                    price += pricingScheme.getPrice(showTime.getStartDateTime().toLocalDate(), cinema.getCinemaClass(),
                            getAgeGroup(), showTime.getMovie().getMovieType(), layout[row][column].getSeatType());
                    ticketList.add(ticket);
                    layout[row][column].setSeatStatus(SeatStatus.TAKEN);

                    break;
                } else {
                    CommonUI.displaySingleMessage("Seat has been taken!\n");
                }
            }
        }
        String transactionId = generateTransactionId(cinema.getCinemaCode());

        for (int j = 0; j < ticketList.size(); j++) {
            Ticket ticket = ticketList.get(j);
            layout[ticket.getRow()][ticket.getColumn()].setTransactionId(transactionId);
        }

        Booking booking = new Booking(movieGoer, price, transactionId, ticketList, cineplex.getName(), showTime);

        BookingUI.displayLayOut(layout);
        BookingUI.displayBookingConfirmInfo(booking);

        Boolean isConfirm = false;

        CommonUI.displaySingleMessage("Confirm booking:\n1. Yes\n2. No\nEnter your choice:");

        choice = input.nextInt();

        switch (choice) {
            case 1:
                isConfirm = true;
                break;
            default:
                break;
        }

        if (isConfirm) {
            ArrayList<Booking> bookingList = (ArrayList<Booking>) Serialization.readSerializedObject(DataPath.BOOKING);
            if (bookingList == null || bookingList.size() == 0) {
                bookingList = new ArrayList<Booking>();
            }
            bookingList.add(booking);
            
            ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);

            Movie movie;
            
            for (int l = 0; l < movieList.size(); l++) {
                if (movieList.get(l).getTitle().equalsIgnoreCase(booking.getShowTime().getMovie().getTitle())) {
                    for (int j = 0; j < booking.tickets().size(); j++) {
                        movieList.get(l).increaseSales();
                    }
                    break;
                }
            }
            Serialization.writeSerializedObject(DataPath.MOVIE, movieList);

            Serialization.writeSerializedObject(DataPath.BOOKING, bookingList);
            
            Serialization.writeSerializedObject(DataPath.CINEPLEX, cineplexList);
            CommonUI.displaySingleMessage("Booking successfully!\n");
        } else {
            CommonUI.displaySingleMessage("Booking unsuccessfully!\n");
        }

    }

    public static void viewHistory(MovieGoer movieGoer) {
        ArrayList<Booking> bookingList = (ArrayList<Booking>) Serialization.readSerializedObject(DataPath.BOOKING);

        if (bookingList == null || bookingList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no booking to view!\n");
            return;
        }
        ArrayList<Booking> bookingListToShow = new ArrayList<Booking>();

        for (int i = 0; i < bookingList.size(); i++) {
            Booking booking = bookingList.get(i);
            if (booking.getMovieGoer().getUsername().equalsIgnoreCase(movieGoer.getUsername())) {
                bookingListToShow.add(booking);
            }
        }

        BookingUI.displayBookingHistory(bookingListToShow);

    }

    private static Integer getNoTicket() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter number of tickets:");
        Integer noTicket = input.nextInt();
        return noTicket;
    }

    private static Integer getRow() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter row:");
        String row = input.next();
        return BookingUI.REVERSE_ROW_MAP.get(row);
    }

    private static Integer getColumn() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter column:");
        Integer column = input.nextInt();
        return column - 1;
    }

    private static String generateTransactionId(String cinemaCode) {
        LocalDateTime localDateNow = LocalDateTime.now();
        String month = localDateNow.getMonthValue() >= 10 ? String.valueOf(localDateNow.getMonthValue())
                : "0" + String.valueOf(localDateNow.getMonthValue());
        String day = localDateNow.getDayOfMonth() >= 10 ? String.valueOf(localDateNow.getDayOfMonth())
                : "0" + String.valueOf(localDateNow.getDayOfMonth());
        String hour = localDateNow.getHour() >= 10 ? String.valueOf(localDateNow.getHour())
                : "0" + String.valueOf(localDateNow.getHour());
        String minute = localDateNow.getMinute() >= 10 ? String.valueOf(localDateNow.getMinute())
                : "0" + String.valueOf(localDateNow.getMinute());

        String transactionId = cinemaCode + String.valueOf(localDateNow.getYear()) + month + day + hour + minute;

        return transactionId;
    }

    private static AgeGroup getAgeGroup() {
        int choice = 0;
        AgeGroup ageGroup;
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("==== Please choose age group ====");
        CommonUI.displaySingleMessage("1. Child\n2. Adult\n3. Senior Citizen\nEnter your choice:");

        choice = input.nextInt();

        switch (choice) {
            case 1:
                ageGroup = AgeGroup.CHILD;
                break;
            case 2:
                ageGroup = AgeGroup.ADULT;
                break;
            case 3:
                ageGroup = AgeGroup.SENIOR_CITIZEN;
                break;
            default:
                ageGroup = AgeGroup.ADULT;
                break;
        }

        return ageGroup;
    }
}
