import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieCollection {
    private ArrayList<Movie> movieList;
    private ArrayList<String> actorsList;
    private Scanner scanner;
    public MovieCollection() {
        movieList = new ArrayList<>();
        actorsList = new ArrayList<>();
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
        for(int i = 1; i < movieList.size(); i++) {
            int index = i;
            Movie removed = movieList.get(i);
            while(index > 0 && (removed.getTitle().compareTo(movieList.get(index - 1).getTitle()) < 0)) {
                movieList.set(index, movieList.get(index - 1));
                index--;
            }
            movieList.set(index, removed);
        }
        System.out.println("Enter title search term: ");
        String term = scanner.nextLine();
        ArrayList<Movie> newList = new ArrayList<>();
        for(Movie movie : movieList) {
            if(movie.getTitle().toLowerCase().indexOf(term.toLowerCase()) != -1) {
                newList.add(movie);
            }
        }
        for(int i = 0; i < newList.size(); i++) {
            System.out.println((i + 1) + ". " + newList.get(i).getTitle());
        }
        if(newList.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            System.out.println("Which movie would you like to learn more about?");
            System.out.println("Enter number: ");
            int num = scanner.nextInt();
            System.out.println("Title: " + newList.get(num - 1).getTitle());
            System.out.println("Runtime: " + newList.get(num - 1).getRunTime());
            System.out.println("Directed by: " + newList.get(num - 1).getDirector());
            System.out.println("Cast: " + newList.get(num - 1).getCast());
            System.out.println("Overview: " + newList.get(num - 1).getOverview());
            System.out.println("User rating: " + newList.get(num - 1).getUserRating());
            scanner.nextLine();
        }
    }

    public void searchCast() {
        System.out.println("Enter a person to search for(first or last name): ");
        String name = scanner.nextLine();
        ArrayList<String> newList = new ArrayList<>();
        ArrayList<Movie> newMovieList = new ArrayList<>();
        for(String actor : actorsList) {
            if(actor.toLowerCase().indexOf(name.toLowerCase()) != -1) {
                newList.add(actor);
            }
        }
        for(int i = 0; i < newList.size(); i++) {
            System.out.println((i + 1) + ". " + newList.get(i));
        }
        if(newList.size() == 0) {
            System.out.println("No results match your search");
        } else {
            System.out.println("Which would you like to see all movies for?");
            System.out.println("Enter number: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            for(int i = 0; i < movieList.size(); i++) {
                if(movieList.get(i).getCast().contains(newList.get(number - 1).toLowerCase())) {
                    newMovieList.add(movieList.get(i));
                    System.out.println((i + 1) + ". " + movieList.get(i).getTitle());
                }
            }
            System.out.println("Which movie would you like to learn more about?");
            System.out.println("Enter number: ");
            int num = scanner.nextInt();
            System.out.println("Title: " + newMovieList.get(num - 1).getTitle());
            System.out.println("Runtime: " + newMovieList.get(num - 1).getRunTime());
            System.out.println("Directed by: " + newMovieList.get(num - 1).getDirector());
            System.out.println("Cast: " + newMovieList.get(num - 1).getCast());
            System.out.println("Overview: " + newMovieList.get(num - 1).getOverview());
            System.out.println("User rating: " + newMovieList.get(num - 1).getUserRating());
            scanner.nextLine();
        }
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
                String runTime = splitData[4];
                String userRating = splitData[5];
                for(int i = 0; i < movieList.size(); i++) {

                    while (fileScanner.hasNext()) {
                        String actors = fileScanner.nextLine();
                        String[] actorList = actors.split("\\|");
                        boolean isDuplicate = false;
                        for (int j = 0; j < actorList.length; j++) {
                            for (int k = 0; k < actorsList.size(); k++) {
                                if (actorList[j].equals(actorsList.get(k))) {
                                    isDuplicate = true;
                                }
                            }
                            if (isDuplicate == false) {
                                actorsList.add(actorList[j]);
                            }
                        }
                    }

                }
                for(int i = 1; i < actorsList.size(); i++) {
                    int index = i;
                    String removed = actorsList.get(i);
                    while(index > 0 && (removed.compareTo(actorsList.get(index - 1)) < 0)) {
                        actorsList.set(index, actorsList.get(index - 1));
                        index--;
                    }
                    actorsList.set(index, removed);
                }


                Movie movie = new Movie(title, cast, director, overview, runTime, userRating);
                movieList.add(movie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println(actorsList);
    }
}
