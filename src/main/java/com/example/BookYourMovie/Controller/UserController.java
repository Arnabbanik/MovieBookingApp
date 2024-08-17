package com.example.BookYourMovie.Controller;

import com.example.BookYourMovie.Entity.LoginDto;
import com.example.BookYourMovie.Entity.User;
import com.example.BookYourMovie.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moviebooking")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginDto.getLoginId(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User sign in successfully",HttpStatus.OK);
    }
}
