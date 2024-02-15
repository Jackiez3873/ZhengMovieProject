import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieCollection {
    private ArrayList<Movie> movieList;
    private Scanner scanner;
    public MovieCollection() {
        movieList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
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
    }




}
