package de.hartz.software.sodevsalaryguide.endpoints;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// @AutoConfigureWebMvc
@ContextConfiguration(
    classes = {HibernateJpaAutoConfiguration.class}) // , WebMvcAutoConfiguration.class
public class RawDataRestControllerTest {

  @Autowired MockMvc mockMvc;

  @Test
  public void getCsvFile_withValidUrl_returnsFile() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/rawdata/csv/2011-chunk-1")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
