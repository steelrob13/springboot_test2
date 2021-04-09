package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test //in class
    void testHistory() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("testing")));   //31:29
    }

    @Test //in class
    void testDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=SpringToDelete")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=SpringToDelete").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("does not exist")));

    }

    @Test
    void testHw1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("testing")));
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=testing").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("does not exist")));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("testing"))));


    }

    @Test
    void testHw2() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=Testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=Testing").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("testing"))));
    }

}