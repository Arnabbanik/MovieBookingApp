package com.example.BookYourMovie.Service;

import com.example.BookYourMovie.Entity.Theater;
import com.example.BookYourMovie.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public Theater createTheater(Theater theater){
        return theaterRepository.save(theater);
    }

    public List<String> getAvailableTicketsByShowTime(Long theaterId, Long movieId){
        Optional<Theater> theaterOptional = theaterRepository.findByTheaterId(theaterId);

        if(theaterOptional.isPresent()){
            Theater theater = theaterOptional.get();

            //Find the show time in the Theater
            Optional<Theater.ShowTime> showTimeOptional = theater.getShowtimes().stream()
                    .filter( s -> s.getMovieId().equals(movieId)).findFirst();

            if(showTimeOptional.isPresent()){
                Theater.ShowTime theaterShowTime = showTimeOptional.get();

                //Get the available seats
                List<String> availableSeats = theaterShowTime.getSeats().stream().filter(seat -> !seat.isBooked())
                        .map(Theater.Seat::getSeatNumber).collect(Collectors.toList());

                return availableSeats;
            }else{
                throw new IllegalArgumentException("ShowTime not found");
            }
        }else{
            throw new IllegalArgumentException("Theater not found");
        }
    }
}
