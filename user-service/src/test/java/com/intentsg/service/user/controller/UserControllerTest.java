package com.intentsg.service.user.controller;

import org.junit.jupiter.api.Test;
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
    void addTourToCart() throws  Exception {
        mockMvc.perform(get("/users/cart/add?userid=11&tourid=2&price=983"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteTourFromCart() throws  Exception {
        mockMvc.perform(get("/users/cart/delete?userid=11&tourid=2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void cleanCart() throws  Exception {
        mockMvc.perform(get("/users/cart/clean?userid=11"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void byAllToursFromCurt() throws  Exception {
        mockMvc.perform(get("/users/cart/buy?userid=11"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void signUp() throws  Exception {
        mockMvc.perform(post("/users/signup")
                .content("{\"userName\": \"YURIYYY\",\"password\": \"11111\"}")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void signIn() throws  Exception {
        mockMvc.perform(post("/users/signin")
                .content("{\"userName\": \"admin\",\"password\": \"admin\"}")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void signOut() throws  Exception {
        mockMvc.perform(get("/users/signout?userid=11"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}