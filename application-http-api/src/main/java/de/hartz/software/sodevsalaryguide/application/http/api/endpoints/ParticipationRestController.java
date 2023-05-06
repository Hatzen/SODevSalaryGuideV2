package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/participations")
class ParticipationRestController {

  @Autowired EvaluatedDataReadRepo evaluatedDataRepo;

  @GetMapping("/overall")
  public long getOverallParicipationCount() {
    return evaluatedDataRepo.getAllSurveyEntries().size();
  }
}
