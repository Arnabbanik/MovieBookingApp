package com.example.BookYourMovie.Repository;

import com.example.BookYourMovie.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {

    User findByEmail(String email);
    User findByLoginId(String loginId);
    void deleteByEmail(String email);
}
