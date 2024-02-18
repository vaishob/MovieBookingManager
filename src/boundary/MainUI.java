package boundary;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Cinema;
import entity.CinemaClass;
import entity.Cineplex;
import entity.DataPath;
import entity.Serialization;

public class MainUI {
	// Program entry
	
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		
//		ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
//		
//		Cinema cinema1 = new Cinema("GV1", CinemaClass.NORMAL);
//		Cinema cinema2 = new Cinema("GV2", CinemaClass.NORMAL);
//		Cinema cinema3 = new Cinema("GV3", CinemaClass.PLATINUM_MOVIE_SUITE);
//	    
//	    Cineplex cineplex1 = new Cineplex("Golden Village Jurong Point");
//	    cineplex1.getCinemas().add(cinema1);
//        cineplex1.getCinemas().add(cinema2);
//        cineplex1.getCinemas().add(cinema3);
//        
//        Cinema cinema4 = new Cinema("GV4", CinemaClass.NORMAL);
//        Cinema cinema5 = new Cinema("GV5", CinemaClass.NORMAL);
//        Cinema cinema6 = new Cinema("GV6", CinemaClass.PLATINUM_MOVIE_SUITE);
//        
//        Cineplex cineplex2 = new Cineplex("Golden Village Vivo");
//        cineplex2.getCinemas().add(cinema4);
//        cineplex2.getCinemas().add(cinema5);
//        cineplex2.getCinemas().add(cinema6);
//        
//        Cinema cinema7 = new Cinema("GV7", CinemaClass.NORMAL);
//        Cinema cinema8 = new Cinema("GV8", CinemaClass.NORMAL);
//        Cinema cinema9 = new Cinema("GV9", CinemaClass.PLATINUM_MOVIE_SUITE);
//        
//        Cineplex cineplex3 = new Cineplex("Golden Village Tampines");
//        cineplex3.getCinemas().add(cinema7);
//        cineplex3.getCinemas().add(cinema8);
//        cineplex3.getCinemas().add(cinema9);
//        
//        cineplexes.add(cineplex1);
//        cineplexes.add(cineplex2);
//        cineplexes.add(cineplex3);
//
//        Serialization.writeSerializedObject(DataPath.CINEPLEX, cineplexes);
//        
//        ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) Serialization.readSerializedObject(DataPath.CINEPLEX);
//        
//        for (int i = 0; i < cineplexList.size(); i++) {
//            Cineplex cineplex = cineplexList.get(i);
//            System.out.println(cineplex.getName());
//            for (int j = 0; j < cineplex.getCinemas().size(); j++) {
//                System.out.println(cineplex.getCinemas().get(j).getCinemaCode());
//            }
//        }

		
		System.out.println("===== Welcome to MOBLIMA =====");
	
		while (choice != 3) {
			System.out.println("1. Administrator");
			System.out.println("2. Movie Goer");
			System.out.println("3. Quit MOBLIMA");
			System.out.println("Enter your choice:");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				AdminUI.displayAdminMainUI();
				break;
			case 2:
				MovieGoerUI.displayMovieGoerMainUI();
				break;
			default:
				System.out.println("Thank for using our MOBLIMA app!");
				break;
			}
		}

		input.close();

	}

}
