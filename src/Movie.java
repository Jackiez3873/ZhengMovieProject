public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private String runTime;
    private String userRating;
    public Movie(String title, String cast, String director, String overview, String runTime, String userRating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runTime = runTime;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getOverview() {
        return overview;
    }

    public String getRunTime() {
        return runTime;
    }

    public String getUserRating() {
        return userRating;
    }
}
