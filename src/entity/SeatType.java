package entity;

public enum SeatType implements LabelledItem {
    /**
    * Normal
    */
   NORMAL("Normal"),
   /**
    * Premium
    */
   PREMIUM("Premium");
 
   /**
    * The label of the seat type
    */
   String label;
   
   private SeatType(String label) {
       this.label = label;
   }

   @Override
   public String getLabel() {
       return label;
   }
}

