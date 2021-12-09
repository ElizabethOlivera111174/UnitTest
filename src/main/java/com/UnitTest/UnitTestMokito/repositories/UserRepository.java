package com.UnitTest.UnitTestMokito.repositories;


import com.UnitTest.UnitTestMokito.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
