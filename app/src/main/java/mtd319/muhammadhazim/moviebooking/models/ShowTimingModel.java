package mtd319.muhammadhazim.moviebooking.models;

public class ShowTimingModel {
    private String cinema = "";
    private int cinemaRes;
    private double lat , lng;
    private String timings = "",completeAddress = "";

    public ShowTimingModel(String cinema, String timings,int cinemaResource,double lat,double lng,String completeAddress) {
        this.cinema = cinema;
        this.timings = timings;
        this.cinemaRes = cinemaResource;
        this.lat = lat;
        this.lng = lng;
        this.completeAddress = completeAddress;
    }


    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public int getCinemaRes() {
        return cinemaRes;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }
}
