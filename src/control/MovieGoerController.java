package control;

import java.util.ArrayList;
import java.util.Scanner;

import boundary.CommonUI;
import boundary.MovieGoerUI;
import entity.DataPath;
import entity.MovieGoer;
import entity.Serialization;

public class MovieGoerController {
    public static void movieGoerSignUp() throws Exception {
        Scanner input = new Scanner(System.in);

        ArrayList<MovieGoer> movieGoerList = (ArrayList<MovieGoer>) Serialization.readSerializedObject(DataPath.MOVIEGOER);

        
        String username = getUsername();
        
        if (movieGoerList != null) {
            for (int i = 0; i < movieGoerList.size(); i++) {
                if (movieGoerList.get(i).getUsername().equals(username)) {
                    CommonUI.displaySingleMessage("Movie goer username already exist!\n");
                    return;
                }
            }
        }
        
		String password = getPassword();
		String name = getName();
		String mobile = getMobile();
		String email = getEmail();
		Integer age = getAge();


        MovieGoer movieGoer = new MovieGoer(username, password, name, mobile, email, age);
        
        boolean isExist = false;

		if (movieGoerList != null) {
			for (int i = 0; i < movieGoerList.size(); i++) {
				if (movieGoerList.get(i).getUsername().equals(movieGoer.getUsername())) {
					isExist = true;
					break;
				}
			}
		} else {
			movieGoerList = new ArrayList<MovieGoer>();
		}
		
		if (!isExist) {
			movieGoerList.add(movieGoer);
			Serialization.writeSerializedObject(DataPath.MOVIEGOER, movieGoerList);
			CommonUI.displaySingleMessage("Sign up successfully!\n");
		} else {
	        CommonUI.displaySingleMessage("Movie goer already exist!\n");

		}
	}

    public static void movieGoerLogIn() throws Exception{
        Scanner input = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = input.nextLine();

        System.out.println("Enter password:");
        String password = input.nextLine();

        ArrayList<MovieGoer> movieGoerList = (ArrayList<MovieGoer>) Serialization.readSerializedObject(DataPath.MOVIEGOER);
        MovieGoer movieGoer = null;
        boolean isAuth = false;
        
        if (movieGoerList != null) {
            for (int i = 0; i < movieGoerList.size(); i++) {
                movieGoer = movieGoerList.get(i);
                if (movieGoer.getUsername().equals(username) && movieGoer.getPassword().equals(password)) {
                    isAuth = true;
                    break;
                }
            }
        }

		if (isAuth) {
			CommonUI.displaySingleMessage("Sign in successfully!\n");
	         MovieGoerUI.displayMovieGoerServicesUI(movieGoer);
		} else {
	         CommonUI.displaySingleMessage("Username or password is not valid!\n");
		}
	}
	
	private static String getUsername() {
	    Scanner input = new Scanner(System.in);
	    CommonUI.displaySingleMessage("Enter username:");
	    String username = input.nextLine();
	    return username;
	}
	
	private static String getPassword() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter password:");
        String password = input.nextLine();
        return password;
    }
	
	private static String getName() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter name:");
        String name = input.nextLine();
        return name;
    }
	
	private static String getMobile() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter mobile number:");
        String mobile = input.nextLine();
        return mobile;
    }
	
	private static String getEmail() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter email:");
        String email = input.nextLine();
        return email;
    }
	
	private static Integer getAge() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter age:");
        Integer age = input.nextInt();
        return age;
    }
}
