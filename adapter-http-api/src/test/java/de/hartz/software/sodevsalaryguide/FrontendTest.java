package de.hartz.software.sodevsalaryguide;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FrontendTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getFrontendIndex_withValidUrl_returnsIndexFile() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/app/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void getFrontendIndex_withInvalidUrl_returns404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/not/existing/path").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getApiOverallCount_withValidUrl_returnsNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/participations/overall").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("1678737792891")));
    }
}