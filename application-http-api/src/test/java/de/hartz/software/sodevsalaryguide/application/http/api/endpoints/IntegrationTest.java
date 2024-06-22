package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.dto.FilterDto;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataWriteRepo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {HttpApiTestConfiguration.class})
@ActiveProfiles({"persistence-test", "http-test"})
public class IntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ParticipationRestController controller;
    @Autowired
    private EvaluatedDataWriteRepo repo;

    @Test
    void getFilteredSurveyEntries_withAllCriteria_returnsOnlyThatOneMatch() throws Exception {

        val surveyEntryMatch = SurveyEntry.builder().abilities(Set.of("Java")).expirienceInYears(new Range(0, 1000)).age(18).yearOfSurvey(2011)
                .country("Germany").highestDegree("Bsc").salary(2.0).build();
        val surveyEntryNoMatch = SurveyEntry.builder().abilities(Set.of("Test")).expirienceInYears(new Range(0, 1000)).age(18).yearOfSurvey(2011)
                .country("Germany").highestDegree("Msc").salary(1.0).build();

        repo.insertAllSurveyEntries(List.of(surveyEntryMatch, surveyEntryNoMatch));


        val filter = new FilterDto(
                Set.of(2011),
                new Range(0, 1000),
                Set.of(Gender.FEMALE),
                Set.of("Java"),
                new Range(0, 1000),
                Set.of("Germany"),
                Set.of("Bsc"));

        mvc.perform(
                        MockMvcRequestBuilders.post(ParticipationRestController.ENDPOINT_URL + ParticipationRestController.FILTERED, filter
                                )
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].salary", containsInAnyOrder("2.0")));
        // .andExpect(content().string(equalTo("1678737792891")));
    }

}
