package com.example.BookYourMovie.Controller;

import com.example.BookYourMovie.Entity.Theater;
import com.example.BookYourMovie.Service.TheaterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @GetMapping("/availableseats/{theaterId}")
    public ResponseEntity<List<String>> getAvailableSeats(
            @PathVariable Long theaterId,
            @RequestParam Long movieId,
            @RequestParam Date showtime) {

        List<String> availableSeats = theaterService.getAvailableTicketsByShowTime(theaterId, movieId);
        return ResponseEntity.ok(availableSeats);
    }

    @PostMapping("/theateradded")
    public ResponseEntity<String> createNewTheater(@RequestBody Theater theater,@RequestParam String movieId){
                theaterService.createTheater(theater,movieId);
                return ResponseEntity.status(HttpStatus.CREATED).body("New theater added");
    }
}
