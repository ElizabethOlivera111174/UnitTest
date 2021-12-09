package com.UnitTest.UnitTestMokito.Service;


import com.UnitTest.UnitTestMokito.models.User;
import com.UnitTest.UnitTestMokito.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepositoryMock;

    @Autowired
    UserService userServiceMock= new UserService(userRepositoryMock);

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void getAll() {
       User mockUser = new User();
        mockUser.setId((long)1);
        mockUser.setName("user");
        mockUser.setLast_name("Last_user");
        mockUser.setEmail("user@gmail.com");
        mockUser.setPassword("1234");

        User mockUser1 = new User();
        mockUser1.setId((long)2);
        mockUser1.setName("user1");
        mockUser1.setLast_name("Last_user1");
        mockUser1.setEmail("user1@gmail.com");
        mockUser1.setPassword("1234");


        List<User> listUser = Arrays.asList(mockUser, mockUser1);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(listUser);
        List<User> Response = userServiceMock.getAll();
        assertFalse(Response.isEmpty());
        assertEquals(2, Response.size());

    }

    @Test
    void getbyEmail() throws Exception {
       User mockUser = new User();
        mockUser.setId((long)1);
        mockUser.setName("user");
        mockUser.setLast_name("Last_user");
        mockUser.setEmail("user@gmail.com");
        mockUser.setPassword("1234");


        when(userRepositoryMock.findByEmail("user@gmail.com")).thenReturn(Optional.of(mockUser));
        Optional<User> response = userServiceMock.findByEmail("user@gmail.com");
        assertEquals("user", response.get().getName());
        assertEquals("Last_user", response.get().getLast_name());
        assertEquals("user@gmail.com", response.get().getEmail());

    }
    @Test
    void getById() throws Exception {
        User mockUser = new User();
        mockUser.setId((long)1);
        mockUser.setName("user");
        mockUser.setLast_name("Last_user");
        mockUser.setEmail("user@gmail.com");
        mockUser.setPassword("1234");


        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(mockUser));
        Optional<User> response = userServiceMock.getById(1L);
        assertEquals("user", response.get().getName());
        assertEquals("Last_user", response.get().getLast_name());
        assertEquals("user@gmail.com", response.get().getEmail());
        assertTrue(response.isPresent());

    }

    @Test
    void saveUser() throws Exception {
        User mockUser = new User();
        mockUser.setId((long)1);
        mockUser.setName("user");
        mockUser.setLast_name("Last_user");
        mockUser.setEmail("user@gmail.com");
        mockUser.setPassword("1234");
        
        when(userRepositoryMock.save(mockUser)).thenReturn(mockUser);
        User Response = userServiceMock.saveUser(mockUser);
        assertEquals("user", Response.getName());
        assertEquals("Last_user", Response.getLast_name());
        assertEquals("user@gmail.com", Response.getEmail());

        
    }

}