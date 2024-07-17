package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.hartz.software.sodevsalaryguide.application.http.api.helper.RangeDeserializer;
import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.dto.FilterDto;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataWriteRepo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {HttpApiTestConfiguration.class})
// Bad performance but clear db every time.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles({"persistence-test", "http-test"})
public class IntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ParticipationRestController controller;
    @Autowired
    private EvaluatedDataWriteRepo repo;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        val surveyEntryMatch = SurveyEntry.builder().abilities(Set.of("Java")).expirienceInYears(new Range(0, 1000)).age(18).yearOfSurvey(2011)
                .country("Germany").highestDegree("Bsc").salary(2.0).gender(Gender.FEMALE).companySize(new Range(0, 1000)).build();
        val surveyEntryNoMatch = SurveyEntry.builder().abilities(Set.of("Test")).expirienceInYears(new Range(0, 1000)).age(18).yearOfSurvey(2011)
                .country("Germany").highestDegree("Msc").salary(1.0).build();

        repo.insertAllSurveyEntries(List.of(surveyEntryMatch, surveyEntryNoMatch));
    }

    @Test
    void getFilteredSurveyEntries_withAllCriteria_returnsOnlyThatOneMatch() throws Exception {
        String requestJson = IOUtils.toString(
                getClass().getResourceAsStream("/requests/filterDto.json"),
                "UTF-8"
        );


        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Range.class, new RangeDeserializer());
        objectMapper.registerModule(module);
        val test = objectMapper.readValue(requestJson, FilterDto.class);

        val result = mvc.perform(MockMvcRequestBuilders.post(ParticipationRestController.ENDPOINT_URL + ParticipationRestController.FILTERED)
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(requestJson));

        String content = result.andReturn().getResponse().getContentAsString();
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resultSet[*].salary", containsInAnyOrder(2.0)));


    }

    @Test
    void getFilteredSurveyEntries_withEmptyCriteria_returnsAll() throws Exception {
        val filter = new FilterDto(
                Map.of(),
                null,
                Set.of(),
                Set.of(),
                null,
                Set.of(),
                Set.of(),
                false, false, false, false, false, false);

        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(filter);

        val result = mvc.perform(MockMvcRequestBuilders.post(ParticipationRestController.ENDPOINT_URL + ParticipationRestController.FILTERED)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resultSet[*].salary", containsInAnyOrder(2.0, 1.0)));
    }
}
