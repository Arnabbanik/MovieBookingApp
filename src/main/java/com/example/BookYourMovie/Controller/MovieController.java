package com.example.BookYourMovie.Controller;

import com.example.BookYourMovie.Entity.Movie;
import com.example.BookYourMovie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/newmovie")
    public ResponseEntity<String> upcomingMovie(@RequestBody Movie movie){
        return movieService.createNewMovie(movie);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovie(){
        return movieService.showAllMovies();
    }
    @GetMapping("/{movieName}")
    public Movie getMovieByName(@PathVariable String movieName){
        return movieService.showMovieByName(movieName);
    }
}
