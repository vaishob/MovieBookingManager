package boundary;

import java.util.ArrayList;

import entity.Movie;
import entity.ReviewRating;

public class MovieUI {
    public static void displayMovieList(ArrayList<Movie> movieList) {
        System.out.println("\nMovie list:");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle());
        }
    }
    
    public static void displayMovieDetail(Movie movie) {
        System.out.println("\nTitle: " + movie.getTitle());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Cast:" );
        for (int i = 0; i < movie.getCast().size(); i++) {
            System.out.println(" *" + movie.getCast().get(i));
        }
        System.out.println("Showing status: " + movie.getShowingStatus().getLabel());
        System.out.println("Release rating: " + movie.getReleaseRating().getLabel());
        System.out.println("Movie type: " + movie.getMovieType().getLabel());
        System.out.println("Duration: " + movie.getDuration());
        System.out.println("Overall rating: " + movie.getOverallRating());
        if (movie.getReviewRatings().size() > 0) {
            System.out.println("Review(s):");
            ArrayList<ReviewRating> reviewList = movie.getReviewRatings();
            for (int j = 0; j < reviewList.size(); j++) {
                ReviewRating review = reviewList.get(j);
                System.out.println("\t" + (j + 1) + ". Review from " + review.getMovieGoer().getName() + " - " + review.getRating());
                System.out.println("\t" + review.getReview());
            }
        }
        System.out.println("Sale: " + movie.getSales() + "\n");
    }
    
    public static void displayMovieUpdateOption() {
        System.out.println("\nWhat would you want to update?");
        System.out.println("1. Title");
        System.out.println("2. Synopsis");
        System.out.println("3. Director");
        System.out.println("4. Cast");
        System.out.println("5. Showing status");
        System.out.println("6. Movie type");
        System.out.println("7. Duration: ");
        System.out.println("8. Release rating: ");
    }
    
    public static void displayTopMovieByOverallRating(ArrayList<Movie> movieList) {
        System.out.println("\nTop movies by overall rating:");
        int size = movieList.size() < 5 ? movieList.size() : 5;
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle() + " - Overall rating: " + movieList.get(i).getOverallRating());
        }
        System.out.println();
    }
    
    public static void displayTopMovieBySales(ArrayList<Movie> movieList) {
        System.out.println("\nTop movies by sales:");
        int size = movieList.size() < 5 ? movieList.size() : 5;
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle() + " - Total sales: " + movieList.get(i).getSales());
        }
        System.out.println();
    }
}
