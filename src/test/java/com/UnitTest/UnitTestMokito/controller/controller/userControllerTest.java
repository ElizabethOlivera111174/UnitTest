package com.UnitTest.UnitTestMokito.controller.controller;

import com.UnitTest.UnitTestMokito.Service.UserService;
import com.UnitTest.UnitTestMokito.controller.UserController;
import com.UnitTest.UnitTestMokito.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.UnitTest.UnitTestMokito.Service.Datos.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;




@WebMvcTest(controllers =UserController.class)
@Import(UserController.class)
public class userControllerTest {
    @MockBean
    UserService userServiceMock;

    @Autowired
    UserController userControllerMock = new UserController(userServiceMock);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void getAllUser() throws Exception {
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

        Mockito.when(userServiceMock.getAll()).thenReturn(listUser);

        mockMvc.perform(get("/user/getAllUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));


    }

     @Test
    void getByIdUser() throws Exception {
         User mockUser = new User();
         mockUser.setId((long)1);
         mockUser.setName("user");
         mockUser.setLast_name("Last_user");
         mockUser.setEmail("usera@gmail.com");
         mockUser.setPassword("1234");



        Mockito.when(userServiceMock.getById(1L)).thenReturn(Optional.of(mockUser));
        mockMvc.perform(get("/user/{id}", 1L))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.name").value("user"));

    }



    @Test
    void save() throws Exception {
        User mockUser = new User();
        mockUser.setId(3L);
        mockUser.setName("user");
        mockUser.setLast_name("Last_user");
        mockUser.setEmail("usera@gmail.com");
        mockUser.setPassword("1234");

        Mockito.when(userServiceMock.saveUser(any())).thenReturn(mockUser);

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("user")))
                .andExpect(jsonPath("$.last_name", is("Last_user")));
        verify(userServiceMock).saveUser(any());


    }

    @Test
    void getByEmail() throws Exception {
        User mockUser = new User();
        mockUser.setId((long)1);
        mockUser.setName("user");
        mockUser.setLast_name("Last_user");
        mockUser.setEmail("user@gmail.com");
        mockUser.setPassword("1234");



        Mockito.when(userServiceMock.findByEmail("user@gmail.com")).thenReturn(Optional.of(mockUser));
        mockMvc.perform(get("/user/getByEmail?email=user@gmail.com", "user@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("user"));
    }


    }


