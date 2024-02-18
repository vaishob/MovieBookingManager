package entity;

public enum ShowingStatus implements LabelledItem {
    /**
    * Coming soon
    */
   COMING_SOON("Coming Soon"),
   /**
    * Preview
    */
   PREVIEW("Preview"),
   /**
    * Now showing
    */
   NOW_SHOWING("Now Showing"),
   /**
    * End of showing
    */
   END_OF_SHOWING("End of Showing");
    
   /**
    * The label of the showing status
    */
   String label;
   
   private ShowingStatus(String label) {
       this.label = label;
   }

   @Override
   public String getLabel() {
       return label;
   }
}
