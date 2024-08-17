package com.example.BookYourMovie.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {

    @Id
    private ObjectId ticketId;
    private ObjectId userId;
    private ObjectId theaterId;
    private ObjectId movieId;
//    private @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date showtime;
    private List<String> seats;
    private double totalPrice;

}
