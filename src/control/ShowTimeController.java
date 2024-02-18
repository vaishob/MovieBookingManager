package control;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import boundary.CommonUI;
import boundary.MovieUI;
import boundary.ShowTimeUI;
import entity.Cinema;
import entity.Cineplex;
import entity.DataPath;
import entity.Movie;
import entity.Serialization;
import entity.ShowTime;

public class ShowTimeController {
    public static void viewShowTimeAdmin() {
        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);
        
        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to add a showtime!\n");
            return;
        }
        
        ShowTimeUI.displayShowTimeAdmin(cineplexList);
            
    }
    
    public static void viewShowTimeMovieGoer() {
        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);
        
        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to add a showtime!\n");
            return;
        }
        
        ShowTimeUI.displayShowTimeMovieGoer(cineplexList);
            
    }
    
    public static void addShowTime() {
        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);
        
        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to add a showtime!\n");
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
            CommonUI.displaySingleMessage("\nThere is no cineplex to add a showtime!\n");
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
        
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);
        choice = MovieController.chooseMovieFromList(movieList);
        
        Movie movie = movieList.get(choice - 1);
        
        String duration = movie.getDuration();
        
        LocalDateTime startDateTime = getDateTime();
        
        ArrayList<ShowTime> showTimeList = cinema.getShowTimes();
        
        if (checkClash(startDateTime, duration, showTimeList, null)) {
            CommonUI.displaySingleMessage("This show time clashes with another show time in the same cinema\n");
        }
        else {
            ShowTime showTime = new ShowTime(startDateTime, movie, cinema.getCinemaCode());
                        
            cinema.getShowTimes().add(showTime);

            cinema.setShowTimes(sortShowTimeList(cinema.getShowTimes()));
         
            cineplex.getCinemas().set(cinemaIndex, cinema);
            
            cineplexList.set(cineplexIndex, cineplex);
            
            Serialization.writeSerializedObject(DataPath.CINEPLEX, cineplexList);
            
            CommonUI.displaySingleMessage("Create show time successfully!\n");
        }
            
    }
    
    public static void updateShowTime() {
        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);
        
        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to update a showtime!\n");
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
            CommonUI.displaySingleMessage("\nThere is no cineplex to add a showtime!\n");
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
        
        if (showTimeList == null || showTimeList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no show time to update!\n");
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
        
        ShowTimeUI.displayShowTimeUpdateOption();
        CommonUI.displaySingleMessage("Enter your choice:");
        choice = input.nextInt();
        
        boolean isValid = true;
        
        switch(choice) {
            case 1:
                ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);
                int movieChoice = MovieController.chooseMovieFromList(movieList);
                
                Movie movie = movieList.get(movieChoice - 1);
                showTime.setMovie(movie);
                if (checkClash(showTime.getStartDateTime(), showTime.getMovie().getDuration(), showTimeList, showTime)) {
                    isValid = false;
                }
                break;
            case 2:
                showTime.setStartDateTime(getDateTime());
                
                if (checkClash(showTime.getStartDateTime(), showTime.getMovie().getDuration(), showTimeList, showTime)) {
                    isValid = false;
                }
                break;
            default: 
                break;
        }
        
        if (isValid) {
            cinema.getShowTimes().set(showTimeIndex, showTime);
            
            cineplex.getCinemas().set(cinemaIndex, cinema);
            
            cineplexList.set(cineplexIndex, cineplex);
            
            Serialization.writeSerializedObject(DataPath.CINEPLEX, cineplexList);
            
            CommonUI.displaySingleMessage("Update show time successfully!\n");
        } else {
            CommonUI.displaySingleMessage("Cannot update show time!\n");
        }
        
   
            
    }
    
    public static void removeShowTime() {
        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);
        
        if (cineplexList == null || cineplexList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no cineplex to remove a showtime!\n");
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
            CommonUI.displaySingleMessage("\nThere is no cineplex to remove a showtime!\n");
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
        
        if (showTimeList == null || showTimeList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no show time to remove!\n");
            return;
        }
        
        choice = 0;
        while (choice < 1 || choice > showTimeList.size()) {
            ShowTimeUI.displayShowTimeList(showTimeList);
            CommonUI.displaySingleMessage("Enter your choice:");
            choice = input.nextInt();
        }
        
        int showTimeIndex = choice - 1;
        
        cinema.getShowTimes().remove(showTimeIndex);
        
        cineplex.getCinemas().set(cinemaIndex, cinema);
        
        cineplexList.set(cineplexIndex, cineplex);
        
        Serialization.writeSerializedObject(DataPath.CINEPLEX, cineplexList);
        
        CommonUI.displaySingleMessage("Remove show time successfully!\n");
            
    }
    
    private static LocalDateTime getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                CommonUI.displaySingleMessage("Enter start date & time (dd/mm/yyyy hh:mm): ");
                String dateTimeString = input.nextLine();
                return LocalDateTime.parse(dateTimeString, formatter);
                
            } catch (DateTimeParseException e) {
                CommonUI.displaySingleMessage("Please enter a valid date time of format: dd/mm/yyyy hh:mm");
            }
        }
    }
    
    public static boolean checkClash(LocalDateTime startDateTime, String duration,  ArrayList<ShowTime> showTimeList, ShowTime currentShowTime) {
        for (ShowTime showTime: showTimeList) {
            LocalDateTime endDateTime = startDateTime.plus(Duration.ofMinutes(Integer.parseInt(duration)));
            LocalDateTime oldShowTimeStart = showTime.getStartDateTime();
            LocalDateTime oldShowTimeEnd = oldShowTimeStart.plus(Duration.ofMinutes(Integer.parseInt(showTime.getMovie().getDuration())));
            boolean isClash = (
                    (oldShowTimeStart.isBefore(endDateTime) && !oldShowTimeStart.isEqual(endDateTime)) &&
                    (startDateTime.isBefore(oldShowTimeEnd) && !startDateTime.isEqual(oldShowTimeEnd)) &&
                    showTime != currentShowTime
                );
                
                if (isClash)
                    return true;
        }
        return false;
    }

    public static ArrayList<ShowTime> sortShowTimeList(ArrayList<ShowTime> showTimeList) {
        for (int i = 1; i < showTimeList.size(); i++)
        {
            for (int j = i; j > 0; j--) {
                if (showTimeList.get(j).getStartDateTime().isBefore(showTimeList.get(j-1).getStartDateTime())) {
                    ShowTime showTime = showTimeList.get(j);
                    showTimeList.set(j, showTimeList.get(j-1));
                    showTimeList.set(j - 1, showTime);
                } else {
                    break;
                }
            }
        }
        return showTimeList;
    }
}