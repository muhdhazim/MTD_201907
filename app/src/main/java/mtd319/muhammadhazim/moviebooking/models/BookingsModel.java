package mtd319.muhammadhazim.moviebooking.models;

import java.io.Serializable;
import java.util.List;

public class BookingsModel implements Serializable {
    private List<Bookings> list;

    public BookingsModel(List<Bookings> list) {
        this.list = list;
    }

    public List<Bookings> getList() {
        return list;
    }

    public static class Bookings {
        private String movie, cinema,date ,time;
        private List<String> seatNo;

        public Bookings(String movie, String cinema, String date, String time,List<String> seatNo) {
            this.movie = movie;
            this.cinema = cinema;
            this.date = date;
            this.time = time;
            this.seatNo = seatNo;
        }

        public String getMovie() {
            return movie;
        }

        public String getCinema() {
            return cinema;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public List<String> getSeatNo() {
            return seatNo;
        }
    }
}
