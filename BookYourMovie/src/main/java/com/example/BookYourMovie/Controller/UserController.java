package com.example.BookYourMovie.Controller;

import com.example.BookYourMovie.Entity.User;
import com.example.BookYourMovie.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moviebooking")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @GetMapping("/getuser/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserById(email);
    }

    @PutMapping("/update")
    public User updateExistingUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteExistingUser(@PathVariable String email){
        return userService.deleteUserById(email);
    }
}
