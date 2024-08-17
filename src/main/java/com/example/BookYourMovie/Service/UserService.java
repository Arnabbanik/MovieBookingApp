package com.example.BookYourMovie.Service;

import com.example.BookYourMovie.Entity.User;
import com.example.BookYourMovie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    //Crud - create,Read,Update,Delete

    //create
    public ResponseEntity<String> addNewUser(User user){
        try{
            List<String> role= new java.util.ArrayList<>(List.of());
            if(null == userRepository.findByEmail(user.getEmail())
                    && null == userRepository.findByLoginId(user.getLoginId())
                    && null == userRepository.findByPhoneNumber(user.getPhoneNumber())){

                if(user.getEmail().equals("ajj@gmail.com")){
                    role.add("ROLE_ADMIN");
                }else{
                    role.add("ROLE_USER");
                }

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(role);
                userRepository.save(user);

                return ResponseEntity.status(HttpStatus.CREATED).body("New User Created");
            }else {
                return ResponseEntity.status(HttpStatus.IM_USED).body("User already exists");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception is"+ e.getMessage());
        }
    }

    //Read
    public User getUserById(String email){
        return userRepository.findByEmail(email);
    }

    //Update
    public User updateUser(User user){
        User updatedUser = userRepository.findByLoginId(user.getLoginId());

        updatedUser.setEmail(user.getEmail());
        updatedUser.setFName(user.getFName());
        updatedUser.setLName(user.getLName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(updatedUser);
    }

    //Deleted
    public ResponseEntity<String> deleteUserById(String email){
        try {
            userRepository.deleteByEmail(email);
            return ResponseEntity.status(HttpStatus.CREATED).body("User is successfully deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured "+ e.getMessage());
        }
    }
}
