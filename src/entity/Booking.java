package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Booking implements Serializable{
    private static final long serialVersionUID = 2L;

    private MovieGoer movieGoer;
    
    private Double price;
    
    private String transactionId;
    
    private ArrayList<Ticket> tickets;
    
    private String cineplexName;
        
    private ShowTime showTime;
    
    public Booking(MovieGoer movieGoer, Double price, String transactionId, ArrayList<Ticket> tickets, String cineplexName, ShowTime showTime) {
        this.movieGoer = movieGoer;
        this.price = price;
        this.transactionId = transactionId;
        this.tickets = tickets;
        this.cineplexName = cineplexName;
        this.showTime = showTime;
    }
    
    public MovieGoer getMovieGoer() {
        return this.movieGoer;
    }
    
    public Double getPrice() {
        return this.price;
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
    public ArrayList<Ticket> tickets() {
        return this.tickets;
    }
    
    public String getCineplexName() {
        return this.cineplexName;
    }
    
    public ShowTime getShowTime() {
        return this.showTime;
    }
}
