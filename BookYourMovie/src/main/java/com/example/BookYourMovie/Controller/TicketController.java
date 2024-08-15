package com.example.BookYourMovie.Controller;

import com.example.BookYourMovie.Entity.Tickets;
import com.example.BookYourMovie.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<Tickets> bookTickets(@RequestParam Long userId,
                                               @RequestParam Long theaterId,
                                               @RequestParam Long movieId,
                                               @RequestParam List<String> SeatNumbers){
        Tickets tickets = ticketService.createTickets(userId,theaterId,movieId,SeatNumbers);
        return  ResponseEntity.ok(tickets);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Tickets>> getTicketsByUserId(@PathVariable Long userId){
        List<Tickets> tickets = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }
}
