package de.hartz.software.sodevsalaryguide.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/participations")
class ParticipationRestController {

    @GetMapping("/overall")
    public long getOverallParicipationCount() {
        return 1678737792891L;
    }
}