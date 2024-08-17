package com.example.BookYourMovie.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "theater")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

    @Id
    private ObjectId theaterId;
    private String theaterName;
    private String location;
    private List<ShowTime> showtimes;

    public static class ShowTime {

        private ObjectId movieId;
        private double price;
        private List<Seat> seats;


        public ObjectId getMovieId() {
            return movieId;
        }

        public void setMovieId(ObjectId movieId) {
            this.movieId = movieId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<Seat> getSeats() {
            return seats;
        }

        public void setSeats(List<Seat> seats) {
            this.seats = seats;
        }

    }

    public static class Seat {

        private String seatNumber;
        private boolean isBooked;

        public String getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public boolean isBooked() {
            return isBooked;
        }

        public void setBooked(boolean booked) {
            isBooked = booked;
        }

        // Getters and Setters
    }
}
