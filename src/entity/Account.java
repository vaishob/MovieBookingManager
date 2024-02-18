package entity;

import java.io.Serializable;

public class Account implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * The username of the movie goer
     */
    private String username;
    /**
     * The password of the movie goer
     */
    private String password;

    /**
     * The name of the movie goer
     */
    private String name;

    public Account(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
    
    /**
     * This method returns the username of the admin
     * 
     * @return the username of the admin
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * This method sets the username of the admin
     * 
     * @param username the new username of the admin
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns the password of the admin
     * 
     * @return the password of the admin
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * This method sets the password of the admin
     * 
     * @param password the new password of the admin
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns the name of the admin
     * 
     * @return the name of the admin
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method sets the name of the admin
     * 
     * @param name the new name of the admin
     */
    public void setName(String name) {
        this.name = name;
    }

}
