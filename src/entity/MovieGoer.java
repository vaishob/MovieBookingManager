package entity;

/**
 * This class contains all the information of a movie goer
 */

public class MovieGoer extends Account {
    
    private static final long serialVersionUID = 6L;

	/**
	 * The mobile number of the movie goer
	 */
	private String mobile;
	
	/**
	 * The email address of the movie goer
	 */
	private String email;
	
	/**
	 * The age of the movie goer
	 */
	private Integer age;
	
	/**
     * This method creates a MobileGoer object with the given username, password, name, mobile, email and age
     */
	
	public MovieGoer(String username, String password, String name, String mobile, String email, Integer age) {
		super(username, password, name);
		this.mobile = mobile;
		this.email = email;
		this.age = age;
	}
	
	/**
     * This method returns the mobile of the movie goer
     * @return the mobile of the movie goer
     */ 
    public String getMobile() {
        return this.mobile;
    }
    
    /**
     * This method sets the mobile of the movie goer
     * @param mobile the new mobile of the movie goer
     */ 
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    /**
     * This method returns the email of the movie goer
     * @return the email of the movie goer
     */ 
    public String getEmail() {
        return this.email;
    }
    
    /**
     * This method sets the email of the movie goer
     * @param email the new email of the movie goer
     */ 
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * This method returns the age of the movie goer
     * @return the age of the movie goer
     */ 
    public Integer getAge() {
        return this.age;
    }
    
    /**
     * This method sets the age of the movie goer
     * @param age the new age of the movie goer
     */ 
    public void setAge(Integer age) {
        this.age = age;
    }

}