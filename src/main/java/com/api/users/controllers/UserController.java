package com.api.users.controllers;

import com.api.users.model.User;
import com.api.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework. @Autowired
private UserService userService;http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class UserController {



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
    public ResponseEntity<?>save(@Valid  @RequestBody User user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.save(user));
        }catch (Exception e){
            String messageError = "No se pudo realizar la operacion" + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageError);
        }
    }

    @PutMapping("/modificar/{id}")

    public ResponseEntity<?>update(@RequestBody User user, @PathVariable Long id) throws Exception {
        try {
            Optional<User> u = userService.findById(id);
            if (u.isPresent()){
                User userdb = u.orElseThrow();
                userdb.setUserName(user.getUserName());
                userdb.setEmail(user.getEmail());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(userService.save(userdb));
            }
        }catch (Exception e ){
            String messageError = "No se pudo realizar la operacion" + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageError);

        }
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            Optional<User> o = userService.findById(id);
            if(o.isPresent()){
                userService.remove(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();

        }catch (Exception e){
            String messageError = "No se pudo realizar la operacion" + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageError);
        }
    }

}
