package de.hartz.software.sodevsalaryguide.application.http.api;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j2
@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    final EvaluatedDataReadRepo evaluatedDataReadRepo;
    final AMQPSendService service;
    final Environment env;

    /**
     * This event is executed as late as conceivably possible to indicate that the application is
     * ready to service requests.
     */
    @SneakyThrows
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (!evaluatedDataReadRepo.getAllAbilities().isEmpty()) {
            log.info("Launched application database is not empty so not importing initial data");
            return;
        }

        val resources = new PathMatchingResourcePatternResolver().getResources("csvchunks/*.csv");
        Arrays.stream(resources).forEach(it -> {
            val fileName = it.getFilename();
            val year = Integer.parseInt(fileName.substring(0, 4));
            val result = new RawDataSetName(fileName, year);
            log.info("queued {}", fileName);

            service.queueDatasetName(result);
        });

        log.info("Launched application start workers..");
    }
}
