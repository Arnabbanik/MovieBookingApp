package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.Tickets;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends MongoRepository<Tickets, ObjectId> {
    List<Tickets> findByUserId(ObjectId userId);
}
