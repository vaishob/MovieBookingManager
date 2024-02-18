package entity;

import java.io.Serializable;

public class Ticket implements Serializable{
    
    private static final long serialVersionUID = 9L;

    
    private Integer row;
    
    private Integer column;
        
    public Ticket(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }
    
    public Integer getRow() {
        return this.row;
    }
    
    public Integer getColumn() {
        return this.column;
    }
    
}
