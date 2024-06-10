package com.globalforge.gdeb;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = EmployeeApplication.class)
public class EmployeeRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
    }

    @Test
    @Order(1)
    public void testGetByBadge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/badge/169272"))
            .andExpect((MockMvcResultMatchers.status().isOk()))
            .andExpect((MockMvcResultMatchers.content().json(
                "{'badgeNumber':169272, 'firstName':'Michael', 'lastName':'Starkie', 'lastUpdated':'2024-06-07T14:55:34Z'}")));
    }

    @Test
    @Order(2)
    public void testInsert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees/save")
            .contentType(MediaType.APPLICATION_JSON).content(
                "{\"badgeNumber\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\"}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Doe")));
    }

    @Test
    @Order(3)
    public void testGetByNewBadge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/badge/1"))
        .andExpect((MockMvcResultMatchers.status().isOk()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Doe")));
    }

    @Test
    @Order(4)
    public void testUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/badge/1")
            .contentType(MediaType.APPLICATION_JSON).content(
                "{\"badgeNumber\": 1, \"firstName\": \"John\", \"lastName\": \"Starkie\"}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Starkie")));
    }
    
    @Test
    @Order(5)
    public void testGetUpdatedBadge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/badge/1"))
        .andExpect((MockMvcResultMatchers.status().isOk()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Starkie")));
    }

    @Test
    @Order(6)
    public void testDeleteBadge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/badge/1")
            .contentType(MediaType.APPLICATION_JSON).content(
                "{\"badgeNumber\": 1, \"firstName\": \"John\", \"lastName\": \"Starkie\"}"))
        .andDo(print())
        .andExpect(status().isOk());

    }
}