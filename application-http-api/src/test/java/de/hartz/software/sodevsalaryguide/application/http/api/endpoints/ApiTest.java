package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {HttpApiTestConfiguration.class})
// TODO: get rid of persistence and use mocks
@ActiveProfiles({"persistence-test", "http-test"})
public class ApiTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getApiOverallCount_withValidUrl_returnsNumber() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.get(ParticipationRestController.ENDPOINT_URL + ParticipationRestController.OVERALL)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("0")));
        // .andExpect(content().string(equalTo("1678737792891")));
    }

}
