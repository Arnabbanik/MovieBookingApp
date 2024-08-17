package com.example.BookYourMovie.Service;

import com.example.BookYourMovie.Entity.Movie;
import com.example.BookYourMovie.Repository.MovieRepository;
import com.example.BookYourMovie.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public ResponseEntity<String> createNewMovie(Movie movie){
        try {
            movieRepository.save(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body("New Movie created");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("there is an error "+ex.getMessage());
        }
    }

    public List<Movie> showAllMovies(){
        return movieRepository.findAll();
    }

    public Movie showMovieByName(String movieName){
        return movieRepository.findByMovieName(movieName);
    }
}
