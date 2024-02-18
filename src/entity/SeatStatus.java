package entity;

public enum SeatStatus implements LabelledItem {
    /**
     * Not taken
     */
    NOT_TAKEN("Not taken"),
    /**
     * Taken
     */
    TAKEN("Taken");
  
    /**
     * The label of the seat type
     */
    String label;
    
    private SeatStatus(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
