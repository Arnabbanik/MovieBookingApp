package com.example.BookYourMovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableMongoRepositories("com.example.BookYourMovie.Repository")
public class BookYourMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookYourMovieApplication.class, args);
	}

}
