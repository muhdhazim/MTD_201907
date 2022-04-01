package mtd319.muhammadhazim.moviebooking.models;

public class MovieDetails {
    private String movieName , synopsis , language ;
    private int resource;

    public MovieDetails(String movieName, String synopsis, String language, int resource) {
        this.movieName = movieName;
        this.synopsis = synopsis;
        this.language = language;
        this.resource = resource;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public int getResource() {
        return resource;
    }
}
