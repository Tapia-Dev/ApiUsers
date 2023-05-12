package com.api.users.controllers;

import com.api.users.model.User;
import com.api.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.findAll());
        } catch (Exception e) {
            String messageError = "No se pudo realizar la operacion" + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageError);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try {
            Optional<User> userOptional = userService.findById(id);
            if (userOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(userService.findById(id).orElseThrow());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            String messageError = "No se pudo realizar la operacion" + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageError);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?>save(@RequestBody User user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.save(user));
        }catch (Exception e){
            String messageError = "No se pudo realizar la operacion" + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageError);
        }
    }



}
