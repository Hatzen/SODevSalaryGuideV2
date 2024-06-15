package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ParticipationRestController.ENDPOINT_URL)
class ParticipationRestController {

  public static final String ENDPOINT_URL = "/api/v1/participations";
  public static final String OVERALL = "/overall";

  @Autowired EvaluatedDataReadRepo evaluatedDataRepo;

  @GetMapping(OVERALL)
  public long getOverallParicipationCount() {
    return evaluatedDataRepo.getAllSurveyEntries().size();
  }
}
