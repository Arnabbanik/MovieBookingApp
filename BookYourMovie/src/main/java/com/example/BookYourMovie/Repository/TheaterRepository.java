package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.Theater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends MongoRepository<Theater,Long> {

    Optional<Theater> findByTheaterId(Long theaterId);
}
