package control;

import java.util.ArrayList;
import java.util.Scanner;

import boundary.CommonUI;
import boundary.MovieUI;
import entity.DataPath;
import entity.Movie;
import entity.MovieGoer;
import entity.MovieType;
import entity.ReleaseRating;
import entity.ReviewRating;
import entity.Serialization;
import entity.ShowingStatus;

public class MovieController {

    public static void viewMovieDetail() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);

        int choice = chooseMovieFromList(movieList);

        if (choice == -1) {
            CommonUI.displaySingleMessage("\nThere is no movie now!\n");
            return;
        }

        Movie movie = movieList.get(choice - 1);

        MovieUI.displayMovieDetail(movie);
        return;
    }
    
    public static void searchMovieDetail() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);

        if (movieList == null || movieList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no movie now!\n");
            return;
        }
        
        String keyword = getKeyWord();

        ArrayList<Movie> movieListToShow = new ArrayList<Movie>();
        
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            if (movie.getTitle().contains(keyword)) {
                movieListToShow.add(movie);
            }
        }
        
        int choice = chooseMovieFromList(movieListToShow);

        if (choice == -1) {
            CommonUI.displaySingleMessage("\nThere is no movie now!\n");
            return;
        }

        Movie movie = movieList.get(choice - 1);

        MovieUI.displayMovieDetail(movie);
        return;
    }


    public static void addMovie() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);
        if (movieList == null) {
            movieList = new ArrayList<Movie>();
        }
        CommonUI.displaySingleMessage("");
        String title = getTitle();
        String synopsis = getSynopsis();
        String director = getDirector();
        ArrayList<String> cast = getCast();
        ShowingStatus showingStatus = getShowingStatus();
        MovieType movieType = getMovieType();
        String duration = getDuration();
        ReleaseRating releaseRating = getReleaseRating();

        Movie movie = new Movie(title, synopsis, director, cast, showingStatus, movieType, releaseRating, duration);

        movieList.add(movie);

        Serialization.writeSerializedObject(DataPath.MOVIE, movieList);

        CommonUI.displaySingleMessage("The movie has been added!\n");
    }

    public static void updateMovie() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);

        int choice = chooseMovieFromList(movieList);

        if (choice == -1) {
            CommonUI.displaySingleMessage("\nThere is no movie to update!\n");
            return;
        }

        int index = choice - 1;
        Movie movie = movieList.get(index);

        MovieUI.displayMovieUpdateOption();
        CommonUI.displaySingleMessage("Enter your choice:");
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();

        switch (choice) {
            case 1:
                movie.setTitle(getTitle());
                break;
            case 2:
                movie.setSynopsis(getSynopsis());
                break;
            case 3:
                movie.setDirector(getDirector());
                break;
            case 4:
                movie.setCast(getCast());
                break;
            case 5:
                movie.setShowingStatus(getShowingStatus());
                break;
            case 6:
                movie.setMovieType(getMovieType());
                break;
            case 7:
                movie.setDuration(getDuration());
                break;
            case 8:
                movie.setReleaseRating(getReleaseRating());
                break;
            default:
                break;
        }

        movieList.set(index, movie);

        Serialization.writeSerializedObject(DataPath.MOVIE, movieList);

        CommonUI.displaySingleMessage("The movie has been updated!\n");

        return;
    }

    public static void removeMovie() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);

        int choice = chooseMovieFromList(movieList);

        if (choice == -1) {
            CommonUI.displaySingleMessage("\nThere is no movie to remove!\n");
            return;
        }

        movieList.remove(choice - 1);

        Serialization.writeSerializedObject(DataPath.MOVIE, movieList);

        CommonUI.displaySingleMessage("The movie has been removed!\n");

        return;
    }

    public static void listTopMovieByRating() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);
        if (movieList == null || movieList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no movie now!\n");
            return;
        }

        for (int i = 1; i < movieList.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (movieList.get(j).getOverallRating() > movieList.get(j - 1).getOverallRating()) {
                    Movie movie = movieList.get(j);
                    movieList.set(j, movieList.get(j - 1));
                    movieList.set(j - 1, movie);
                } else {
                    break;
                }
            }
        }

        MovieUI.displayTopMovieByOverallRating(movieList);
    }

    public static void listTopMovieBySales() {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);
        if (movieList == null || movieList.size() == 0) {
            CommonUI.displaySingleMessage("\nThere is no movie now!\n");
            return;
        }

        for (int i = 1; i < movieList.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (movieList.get(j).getSales() > movieList.get(j - 1).getSales()) {
                    Movie movie = movieList.get(j);
                    movieList.set(j, movieList.get(j - 1));
                    movieList.set(j - 1, movie);
                } else {
                    break;
                }
            }
        }

        MovieUI.displayTopMovieBySales(movieList);

    }

    public static void writeReview(MovieGoer movieGoer) {
        ArrayList<Movie> movieList = (ArrayList<Movie>) Serialization.readSerializedObject(DataPath.MOVIE);

        int choice = chooseMovieFromList(movieList);

        if (choice == -1) {
            CommonUI.displaySingleMessage("\nThere is no movie to write review!\n");
            return;
        }

        int index = choice - 1;
        Movie movie = movieList.get(index);

        ReviewRating review = new ReviewRating(movieGoer,getReview());
        
        movie.addReviewRating(review);
        Serialization.writeSerializedObject(DataPath.MOVIE, movieList);
    }
    
    public static int chooseMovieFromList(ArrayList<Movie> movieList) {
        int choice = -1;
        if (movieList == null || movieList.size() == 0) {
            return choice;
        }

        choice = 0;
        Scanner input = new Scanner(System.in);
        while (choice < 1 || choice > movieList.size()) {
            MovieUI.displayMovieList(movieList);
            CommonUI.displaySingleMessage("Enter your choice:");
            choice = input.nextInt();
        }
        return choice;
    }

    private static String getTitle() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter title:");
        String title = input.nextLine();
        return title;
    }

    private static String getSynopsis() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter synopsis:");
        String synopsis = input.nextLine();
        return synopsis;
    }

    private static String getDirector() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter director:");
        String director = input.nextLine();
        return director;
    }

    private static ArrayList<String> getCast() {
        ArrayList<String> cast = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter cast size:");
        Integer castSize = input.nextInt();
        input.nextLine();
        for (int i = 0; i < castSize; i++) {
            CommonUI.displaySingleMessage("Enter cast's name " + (i + 1) + ":");
            String castName = input.nextLine();
            cast.add(castName);
        }

        return cast;
    }

    private static ShowingStatus getShowingStatus() {
        int choice = 0;
        ShowingStatus showingStatus;
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("==== Please choose showing status ====");
        CommonUI.displaySingleMessage(
                "1. Coming soon\n2. Preview\n3. Now showing\n4. End of showing\nEnter your choice:");

        choice = input.nextInt();

        switch (choice) {
            case 1:
                showingStatus = ShowingStatus.COMING_SOON;
                break;
            case 2:
                showingStatus = ShowingStatus.PREVIEW;
                break;
            case 3:
                showingStatus = ShowingStatus.NOW_SHOWING;
                break;
            case 4:
                showingStatus = ShowingStatus.END_OF_SHOWING;
                break;
            default:
                showingStatus = ShowingStatus.END_OF_SHOWING;
                break;
        }

        return showingStatus;
    }

    private static MovieType getMovieType() {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("==== Please choose movie type ====");
        MovieType movieType;

        CommonUI.displaySingleMessage("1. Regular\n2. Blockbuster\n3. 3D\nEnter your choice:");

        choice = input.nextInt();

        switch (choice) {
            case 1:
                movieType = MovieType.REGULAR;
                break;
            case 2:
                movieType = MovieType.BLOCKBUSTER;
                break;
            case 3:
                movieType = MovieType._3D;
                break;
            default:
                movieType = MovieType.REGULAR;
                break;
        }

        return movieType;
    }

    private static ReleaseRating getReleaseRating() {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("==== Please choose release rating ====");

        ReleaseRating releaseRating;
        
        CommonUI.displaySingleMessage("1. G\n2. PG\n3. PG13\n4. NC16\n5. M18\n6. R21\nEnter your choice:");

        choice = input.nextInt();

        switch (choice) {
            case 1:
                releaseRating = ReleaseRating.G;
                break;
            case 2:
                releaseRating = ReleaseRating.PG;
                break;
            case 3:
                releaseRating = ReleaseRating.PG13;
                break;
            case 4:
                releaseRating = ReleaseRating.NC16;
                break;
            case 5:
                releaseRating = ReleaseRating.M18;
                break;
            case 6:
                releaseRating = ReleaseRating.R21;
                break;
            default:
                releaseRating = ReleaseRating.G;
                break;
        }

        return releaseRating;
    }

    private static String getDuration() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter duration (in minutes):");
        String duration = input.nextLine();
        return duration;
    }
    
    private static String getReview() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter review:");
        String review = input.nextLine();
        return review;
    }
    
    private static String getKeyWord() {
        Scanner input = new Scanner(System.in);
        CommonUI.displaySingleMessage("Enter a key word:");
        String keyWord = input.nextLine();
        return keyWord;
    }


}
