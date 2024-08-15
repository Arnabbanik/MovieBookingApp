package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
    Movie findByMovieName(String movieName);
}
