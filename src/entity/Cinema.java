package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
    private static final long serialVersionUID = 3L;

    private String cinemaCode;
    
    private CinemaClass cinemaClass;
    
    private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
    
    public Cinema(String cinemaCode, CinemaClass cinemaClass) {
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
    }
    
    public String getCinemaCode() {
        return this.cinemaCode;
    }
    
    public CinemaClass getCinemaClass() {
        return this.cinemaClass;
    }
    
    public ArrayList<ShowTime> getShowTimes() {
        return this.showTimes;
    }
    
    public void setShowTimes(ArrayList<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }
}
