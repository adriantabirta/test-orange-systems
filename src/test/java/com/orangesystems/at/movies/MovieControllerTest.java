package com.orangesystems.at.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void popularNotEmpty() throws Exception {
        MvcResult result = mockMvc.perform(get("/movies/popular")).andExpect(status().isOk()).andReturn();
        Assert.isTrue(!result.getResponse().getContentAsString().isEmpty(), "response is empty");
        Assert.isTrue(result.getResponse().getContentAsString().contains("container"), "does not contains container");
    }

    @Test
    void topRatedNotEmpty() throws Exception {
        MvcResult result = mockMvc.perform(get("/movies/top-rated")).andExpect(status().isOk()).andReturn();
        Assert.isTrue(!result.getResponse().getContentAsString().isEmpty(), "response is empty");
        Assert.isTrue(result.getResponse().getContentAsString().contains("container"), "does not contains container");
    }

    @Test
    void findByIdNotEmpty() throws Exception {
        MvcResult result = mockMvc.perform(get("/movies/76341")).andExpect(status().isOk()).andReturn();
        Assert.isTrue(!result.getResponse().getContentAsString().isEmpty(), "response is empty");
        Assert.isTrue(result.getResponse().getContentAsString().contains("container"), "does not contains container");
    }
}
