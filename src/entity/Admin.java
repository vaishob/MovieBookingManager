package entity;

/**
 * This class contains all the information of a admin
 */

public class Admin extends Account {
    /**
     * This method creates a Admin object with the given username, password and name
     * @param username the username of the admain
     * @param password the password of the admin
     * @param name the name of the admin
     */
    public Admin(String username, String password, String name) {
        super(username, password, name); 
    }
    
}
