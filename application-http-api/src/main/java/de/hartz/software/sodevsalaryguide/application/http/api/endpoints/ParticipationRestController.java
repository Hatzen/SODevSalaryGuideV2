package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.application.http.api.model.dto.FilterValuesDTO;
import de.hartz.software.sodevsalaryguide.application.http.api.model.dto.ResultSetForYearDTO;
import de.hartz.software.sodevsalaryguide.core.model.dto.FilterDto;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(ParticipationRestController.ENDPOINT_URL)
class ParticipationRestController {

    public static final String ENDPOINT_URL = "/api/v1/participations";
    public static final String OVERALL = "/overall";
    public static final String FILTERED = "/filtered";
    public static final String VALUES = "/values";

    @Autowired
    EvaluatedDataReadRepo evaluatedDataRepo;

    @GetMapping(value = OVERALL,
            produces = "application/json")
    public long getOverallParicipationCount() {
        return evaluatedDataRepo.getAllSurveyEntries().size();
    }

    @GetMapping(value = VALUES, produces = "application/json")
    public Object getFilterValues() {
        return new FilterValuesDTO(
                evaluatedDataRepo.getAllCountries(),
                evaluatedDataRepo.getAllEducations(),
                evaluatedDataRepo.getAllAbilities()
        );
    }

    @PostMapping(FILTERED)
    public List<ResultSetForYearDTO> getFilteredSurveyEntries(@RequestBody FilterDto filterDto) {
        val allData = evaluatedDataRepo.getMatchingSurveyEntries(filterDto);

        val dummyInvalidCount = 20000;
        
        return allData.stream().collect(Collectors.groupingBy(t -> t.getYearOfSurvey()))
                .entrySet().stream()
                .map(it -> ResultSetForYearDTO.builder()
                        .year(it.getKey())
                        // TODO: determine properly
                        .invalidEntryCount(dummyInvalidCount)
                        .resultSet(it.getValue())
                        .overallEntryCount(it.getValue().size() + dummyInvalidCount)
                        .build()
                ).collect(Collectors.toList());
    }
}
