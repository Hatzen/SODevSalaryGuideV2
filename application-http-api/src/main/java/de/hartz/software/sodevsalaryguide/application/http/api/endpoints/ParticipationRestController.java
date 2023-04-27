package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.adapter.persistence.repo.EvaluatedDataRepoJpa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/participations")
class ParticipationRestController {

  @Autowired EvaluatedDataRepoJpa evaluatedDataRepoJpa;

  @GetMapping("/overall")
  public long getOverallParicipationCount() {
    return 1678737792891L;
  }
}
