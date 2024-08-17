package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Movie findByMovieName(String movieName);
    Movie findByMovieId(ObjectId movieId);
}
