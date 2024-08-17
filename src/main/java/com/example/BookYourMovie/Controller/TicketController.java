package com.example.BookYourMovie.Controller;

import com.example.BookYourMovie.Entity.Tickets;
import com.example.BookYourMovie.Service.TicketService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/booktickets")
    public ResponseEntity<String> bookTickets(@RequestParam String userId,
                                               @RequestParam String theaterId,
                                               @RequestParam String movieId,
                                               @RequestParam List<String> SeatNumbers){
        try{
            Tickets tickets = ticketService.createTickets(userId,theaterId,movieId,SeatNumbers);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Ticket booking successfull");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ticket booking failed due to "+e.getMessage());
        }

    }

    @GetMapping("/user")
    public ResponseEntity<List<Tickets>> getTicketsByUserId(@RequestParam String userId){
        List<Tickets> tickets = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }
}
