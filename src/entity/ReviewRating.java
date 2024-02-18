package entity;

import java.io.Serializable;
import java.util.Scanner;

public class ReviewRating implements Serializable {
    
    private static final long serialVersionUID = 7L;

    /**
	 * The minimum possible rating value
	 */
	public static final int MIN_RATING = 1;

	/**
	 * The maximum possible rating value
	 */
	public static final int MAX_RATING = 5;

    /**
	 * The movie goer who made the review rating
	 */
	private MovieGoer movieGoer;

    /**
	 * The rating
	 */
    private Integer rating;
    
    private String review;

    /**
	 * Creates a {@code ReviewRating} object with the given movie goer, review and rating
	 * @param movieGoer the movie goer who made the review rating
	 */
    public ReviewRating(MovieGoer movieGoer, String review) {
        this.movieGoer = movieGoer;

        this.review = review;
        
        Scanner input = new Scanner(System.in);
		
		System.out.println("Enter rating from 1 to 5:");
		try { 
            Integer rating = input.nextInt();
            if (rating > 5) {
                this.rating = 5;
            } else if (rating < 0) {
                this.rating = 0;
            } else {
                this.rating = rating;
            }
        } catch(Exception e) {
            this.rating = 0;
        }

    }

    public Integer getRating() {
        return this.rating;
    }

    public MovieGoer getMovieGoer() {
        return this.movieGoer;
    }
    
    public String getReview() {
        return this.review;
    }
}
