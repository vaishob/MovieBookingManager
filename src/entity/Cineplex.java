package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
    
    private static final long serialVersionUID = 4L;

    private String name;
    
    private ArrayList<Cinema> cinnemas = new ArrayList<Cinema>();
    
    public Cineplex(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public ArrayList<Cinema> getCinemas() {
        return this.cinnemas;
    }
}
