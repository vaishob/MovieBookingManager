package entity;

import java.io.Serializable;

public class Seat implements Serializable{
    public static final long serialVersionUID = 10L;
    private SeatType seatType;
    private String transactionId;
    private SeatStatus seatStatus;
    
    public Seat(String transactionId, SeatType seatType) {
        this.transactionId = transactionId;
        this.seatType = seatType;
        this.seatStatus = SeatStatus.NOT_TAKEN;
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public SeatType getSeatType() {
        return this.seatType;
    }
    
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
    
    public SeatStatus getSeatStatus() {
        return this.seatStatus;
    }
    
    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
}
