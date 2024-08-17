package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByEmail(String email);
    User findByLoginId(String loginId);
    void deleteByEmail(String email);
    Long findByPhoneNumber(Long phoneNumber);
}
