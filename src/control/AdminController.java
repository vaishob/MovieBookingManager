package control;

import java.util.ArrayList;
import java.util.Scanner;

import boundary.AdminUI;
import boundary.CommonUI;
import entity.Admin;
import entity.DataPath;
import entity.Serialization;

public class AdminController{
	public static void adminSignUp() throws Exception {
		Scanner input = new Scanner(System.in);
		CommonUI.displaySingleMessage("Please enter the secret key to sign up as a admin:");
		if (!input.nextLine().equals("SC2002")) {
		    CommonUI.displaySingleMessage("You are not allowed to sign up as a admin");
			return;
		}

		ArrayList<Admin> adminList = (ArrayList<Admin>) Serialization.readSerializedObject(DataPath.ADMIN);
		
		String username = getUsername();
		String password = getPassword();
		String name = getName();

		Admin admin;
		admin = new Admin(username, password, name);
		
		boolean isExist = false;

		if (adminList != null) {
			for (int i = 0; i < adminList.size(); i++) {
				if (adminList.get(i).getUsername().equals(admin.getUsername())) {
					isExist = true;
					break;
				}
			}
		} else {
			adminList = new ArrayList<Admin>();
		}
		
		if (!isExist) {
			adminList.add(admin);
			Serialization.writeSerializedObject(DataPath.ADMIN, adminList);
	        CommonUI.displaySingleMessage("Sign up successfully!\n");

		} else {
	        CommonUI.displaySingleMessage("Admin already exist!\n");

		}	
	}
	
	public static void adminLogIn() throws Exception{
		
		String username = getUsername();
		String password = getPassword();
				
		ArrayList<Admin> adminList = (ArrayList<Admin>) Serialization.readSerializedObject(DataPath.ADMIN);
		Admin admin = null;
		boolean isAuth = false;
		
		if (adminList != null) {
			for (int i = 0; i < adminList.size(); i++) {
				admin = adminList.get(i);
				if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
					isAuth = true;
					break;
				}
			}
		}

		if (isAuth) {
	        CommonUI.displaySingleMessage("Sign in successfully!\n");
			AdminUI.displayAdminServicesUI(admin);
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
	
}
