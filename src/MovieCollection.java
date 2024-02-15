import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieCollection {
    private ArrayList<Movie> movieList;
    private ArrayList<String> movieTitleList;
    private ArrayList<String> movieCastList;
    private Scanner scanner;
    public MovieCollection() {
        movieList = new ArrayList<>();
        scanner = new Scanner(System.in);
        start();
    }

    public void start() {
        readData();
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();
            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    public void searchTitles() {
        System.out.println("Enter title search term: ");
        String term = scanner.nextLine();
        ArrayList<String> newList = new ArrayList<>();
        for(String title : movieTitleList) {
            if(title.toLowerCase().indexOf(term.toLowerCase()) != -1) {
                newList.add(title);
            }
        }
        for(int i = 0; i < newList.size(); i++) {
            System.out.println((i + 1) + newList.get(i));
        }
        if(newList.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            System.out.println("Which movie would you like to learn more about?");
            System.out.println("Enter number: ");
            int num = scanner.nextInt();

        }
    }

    public void searchCast() {

    }

    private void readData() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runTime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);

                Movie movie = new Movie(title, cast, director, overview, runTime, userRating);
                movieList.add(movie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        for(Movie movie : movieList) {
            movieTitleList.add(movie.getTitle());
            movieCastList.add(movie.getCast());
        }

//        for(int i = 1; i < movieTitleList.size(); i++) {
//            int index = i;
//            String removed = movieTitleList.get(i);
//            while(index > 0 && (removed.compareTo(movieTitleList.get(index - 1)) < 0)) {
//                movieTitleList.set(index, movieTitleList.get(index - 1));
//                index--;
//            }
//            movieTitleList.set(index, removed);
//        }
//        for(int i = 1; i < movieCastList.size(); i++) {
//            int index = i;
//            String removed = movieCastList.get(i);
//            while(index > 0 && (removed.compareTo(movieCastList.get(index - 1)) < 0)) {
//                movieCastList.set(index, movieCastList.get(index - 1));
//                index--;
//            }
//            movieCastList.set(index, removed);
//        }



        for(int i = 1; i < movieList.size(); i++) {
            int index = i;
            Movie removed = movieList.get(i);
            while(index > 0 && (removed.getTitle().compareTo(movieList.get(index - 1).getTitle()) < 0)) {
                movieList.set(index, movieList.get(index - 1));
                index--;
            }
            movieList.set(index, removed);
        }
        for(int i = 1; i < movieList.size(); i++) {
            int index = i;
            Movie removed = movieList.get(i);
            while(index > 0 && (removed.getCast().compareTo(movieList.get(index - 1).getCast()) < 0)) {
                movieList.set(index, movieList.get(index - 1));
                index--;
            }
            movieList.set(index, removed);
        }
    }
}
