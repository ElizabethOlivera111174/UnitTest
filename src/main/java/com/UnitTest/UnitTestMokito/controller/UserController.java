package com.UnitTest.UnitTestMokito.controller;


import com.UnitTest.UnitTestMokito.Service.UserService;
import com.UnitTest.UnitTestMokito.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService _userService;

    public UserController(UserService userService) {
        _userService=userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(_userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(_userService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<User> SaveUser(@Valid @RequestBody User user){
        return ResponseEntity.ok(_userService.saveUser(user));
    }
   @GetMapping(path = "/getByEmail")
    public ResponseEntity<Optional<User>> getbyEmail(@RequestParam String email) {

        return ResponseEntity.ok(_userService.findByEmail(email));
    }

}
