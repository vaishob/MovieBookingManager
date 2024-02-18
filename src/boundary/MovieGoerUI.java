package boundary;

import java.util.Scanner;

import control.BookingController;
import control.MovieController;
import control.MovieGoerController;
import control.ShowTimeController;
import entity.MovieGoer;

public class MovieGoerUI {
		
		private static Scanner input = new Scanner(System.in);

		
		public static  void displayMovieGoerMainUI() {
			int choice = 0;
			System.out.println("===== Welcome to MovieGoer UI =====");

			while (choice != 3) {
				System.out.println("1. Log in");
				System.out.println("2. Sign up");
				System.out.println("3. Quit MovieGoer UI");
				System.out.println("Enter your choice:");
				
				choice = input.nextInt();

				switch (choice) {
				case 1:
					try {
						MovieGoerController.movieGoerLogIn();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						MovieGoerController.movieGoerSignUp();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
			
		}
		
		public static void displayMovieGoerServicesUI(MovieGoer movieGoer) {
	        int choice = 0;
	        System.out.println("Hi " + movieGoer.getName());
	        System.out.println("===== What would you like to do? =====");

	        while (choice != 8) {
	            System.out.println("1. View movie showtimes");
	            System.out.println("2. Search movie");
	            System.out.println("3. Book a ticket");
	            System.out.println("4. View movie details");
	            System.out.println("5. List top 5 movies");
	            System.out.println("6. View booking history");
	            System.out.println("7. Write review");
	            System.out.println("8. Exit");
	            System.out.println("Enter your choice:");

	            choice = input.nextInt();

	            switch (choice) {
	                case 1: 
	                    // 1. View movie showtimes
	                    try {
                            ShowTimeController.viewShowTimeMovieGoer();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
	                case 2: 
                        // 1. Search movie 
                        try {
                            MovieController.searchMovieDetail();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
	                case 3: 
	                    // 2. Book a ticket
	                    try {
	                        BookingController.bookingTicket(movieGoer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	                    break;
    	            case 4:
    	                // 3. View movie details
    	                try {
    	                    MovieController.viewMovieDetail();
    	                } catch (Exception e) {
    	                    e.printStackTrace();
    	                }
    	                break;
    	            case 5:
    	                // 4. List top 5 movies
    	                displayMovieGoerListTopMovieServicesUI();
    	                break;
    	            case 6: 
    	                // 5. View booking history
    	                try {
                            BookingController.viewHistory(movieGoer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
    	                break;
    	            case 7: 
                        // 6. Write review
                        try {
                            MovieController.writeReview(movieGoer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
    	            default:
    	                break;
    	            }
	        }
	    }

		public static void displayMovieGoerListTopMovieServicesUI() {
	        int choice = 0;
	        System.out.println("Top 5 movies by ... ");

	        while (choice != 3) {
	            System.out.println("1. Ratings");
	            System.out.println("2. Ticket sales");
	            System.out.println("3. Exit");
	            System.out.println("Enter your choice:");

	            choice = input.nextInt();

	            switch (choice) {
	                case 1:
	                    try {
	                        MovieController.listTopMovieByRating();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                    break;
	                case 2:
	                    try {
	                        MovieController.listTopMovieBySales();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                    break;
	                default:
	                    break;
	            }
	        }
	    }
}

