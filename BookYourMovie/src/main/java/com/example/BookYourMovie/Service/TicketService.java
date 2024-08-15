package com.example.BookYourMovie.Service;

import com.example.BookYourMovie.Entity.Movie;
import com.example.BookYourMovie.Entity.Theater;
import com.example.BookYourMovie.Entity.Tickets;
import com.example.BookYourMovie.Repository.MovieRepository;
import com.example.BookYourMovie.Repository.TheaterRepository;
import com.example.BookYourMovie.Repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketsRepository ticketsRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;



    public Tickets createTickets(Long userId,Long theaterId, Long movieId, List<String> seatNumbers){

        Optional<Theater> theaterOptional = theaterRepository.findById(theaterId);

        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if(theaterOptional.isPresent() && movieOptional.isPresent()){
            Theater theater = theaterOptional.get();

            //Find the showtime in theater
            Optional<Theater.ShowTime> showTimeOptional = theater.getShowtimes().stream()
                    .filter(s -> s.getMovieId().equals(movieId)).findFirst();

            if(showTimeOptional.isPresent()) {
                Theater.ShowTime theaterShowTime = showTimeOptional.get();

                //check seat availability
                for(String seatNumber: seatNumbers){
                    boolean seatAvailable = theaterShowTime.getSeats().stream()
                            .anyMatch(seat -> seat.getSeatNumber().equals(seatNumber) && !seat.isBooked());

                    if(!seatAvailable){
                        throw new IllegalArgumentException("Seats are already booked");
                    }
                }

                //Marked seat as booked
                theaterShowTime.getSeats().forEach(seat -> {
                    if(seatNumbers.contains(seat.getSeatNumber())){
                        seat.setBooked(true);
                    }
                });

                //save the updated theater
                theaterRepository.save(theater);

                //Calculate total ticket cost
                double totalprice = seatNumbers.size() * theaterShowTime.getPrice();
                
                //create booking record
                Tickets ticket = new Tickets();
                ticket.setUserId(userId);
                ticket.setTheaterId(theaterId);
                ticket.setMovieId(movieId);
//                ticket.setShowtime(showtime);
                ticket.setSeats(seatNumbers);
                ticket.setTotalPrice(totalprice);

                return ticketsRepository.save(ticket);
            }else{
                throw new IllegalArgumentException("Movie Not Found");
            }
        }else{
            throw new IllegalArgumentException("Theater Movie Not Found");
        }
    }


    public List<Tickets> getTicketsByUserId(Long userId){
        return ticketsRepository.findByUserId(userId);
    }
}
