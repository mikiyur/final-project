package com.intentsg.service.user.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    UserController userController;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void getUserById() throws  Exception {
        mockMvc.perform(get("/users/11")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getToursIdByUserId() throws  Exception {
        mockMvc.perform(get("/users/tours/11")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addTourToCart() {
    }

    @Test
    void deleteTourFromCart() {
    }

    @Test
    void cleanCart() {
    }

    @Test
    void byAllToursFromCurt() {
    }

    @Test
    void signUp() {
    }

    @Test
    void signIn() {
    }

    @Test
    void signOut() {
    }
}