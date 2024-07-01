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

/**
 * For JUnit tests.
 * 
 * @author Michael C. Starkie
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = EmployeeApplication.class)
public class EmployeeRestControllerTest {

    /** The Mock MVC. */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Sets up JUnit before each test.
     */
    @BeforeEach
    public void setUp() {
    }

    /**
     * Insert record.
     * 
     * @throws Exception
     */
    @Test
    @Order(2)
    public void testInsert() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.post("/employees/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"badgeNumber\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\"}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Doe")));
    }

    /**
     * Get by badge number 1.
     * 
     * @throws Exception
     */
    @Test
    @Order(3)
    public void testGetByNewBadge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/badge/1"))
            .andExpect((MockMvcResultMatchers.status().isOk()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Doe")));
    }

    /**
     * Update a record.
     * 
     * @throws Exception
     */
    @Test
    @Order(4)
    public void testUpdate() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.put("/employees/badge/1")
                .contentType(MediaType.APPLICATION_JSON).content(
                    "{\"badgeNumber\": 1, \"firstName\": \"John\", \"lastName\": \"Starkie\"}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Starkie")));
    }

    /**
     * Get updated badge 1.
     * 
     * @throws Exception
     */
    @Test
    @Order(5)
    public void testGetUpdatedBadge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/badge/1"))
            .andExpect((MockMvcResultMatchers.status().isOk()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.badgeNumber", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Starkie")));
    }

    /**
     * Delete a record.
     * 
     * @throws Exception
     */
    @Test
    @Order(6)
    public void testDeleteBadge() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/employees/badge/1")
                .contentType(MediaType.APPLICATION_JSON).content(
                    "{\"badgeNumber\": 1, \"firstName\": \"John\", \"lastName\": \"Starkie\"}"))
            .andDo(print()).andExpect(status().isOk());
    }
}
