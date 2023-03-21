package de.hartz.software.sodevsalaryguide.endpoints;

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
public class RawDataRestControllerTest {

  @Autowired private MockMvc mvc;

  @Test
  public void getCsvFile_withValidUrl_returnsFile() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.get("/api/v1/rawdata/csv/2011-chunk-1")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
