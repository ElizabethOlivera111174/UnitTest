package com.UnitTest.UnitTestMokito.Service;

import com.UnitTest.UnitTestMokito.models.User;
import com.UnitTest.UnitTestMokito.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository _userRepository) {
        userRepository=_userRepository;
    }

    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }


    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    }

