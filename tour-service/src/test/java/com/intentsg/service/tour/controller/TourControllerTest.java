package com.intentsg.service.tour.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TourController.class)
class TourControllerTest {

    @MockBean
    TourController tourController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void test1() throws Exception {
        mockMvc.perform(get("/tours/test")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getTourById() throws Exception {
        mockMvc.perform(get("/tours/id/11")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllTours() throws Exception {
        mockMvc.perform(get("/tours/?minPrice=100&maxPrice=1000&page=0&size=6&sort=price,desc"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getUserTours() throws Exception {
        mockMvc.perform(get("/tours/userid/11"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}