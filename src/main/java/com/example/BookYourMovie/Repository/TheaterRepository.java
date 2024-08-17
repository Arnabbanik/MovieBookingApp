package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.Theater;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends MongoRepository<Theater, ObjectId> {

    Optional<Theater> findByTheaterId(Long theaterId);
}
