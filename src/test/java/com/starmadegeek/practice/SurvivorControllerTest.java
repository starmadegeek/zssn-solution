package com.starmadegeek.practice;

import com.starmadegeek.practice.entities.Survivor;
import com.starmadegeek.practice.services.SurvivorService;
import com.starmadegeek.practice.web.SurvivorController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(SurvivorController.class)
public class SurvivorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SurvivorService survivorService;


    @Test
    public void testGetSurvivor() throws Exception {
        List<Survivor> survivors = Arrays.asList(
                Survivor.builder()
                        .name("name1")
                        .build(),
                Survivor.builder()
                        .name("name2")
                        .build()
        );

        when(survivorService.getAllSurvivors()).thenReturn(survivors);

        mockMvc.perform(MockMvcRequestBuilders.get("/survivor"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("name1"));
    }

    @Test
    public void testGetSurvivorWithId() throws Exception {
        Survivor survivor = Survivor.builder()
                        .name("name1").id(1L).build();
        when(survivorService.getSurvivorById(1L)).thenReturn(survivor);

        mockMvc.perform(MockMvcRequestBuilders.get("/survivor/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"));
    }
}
