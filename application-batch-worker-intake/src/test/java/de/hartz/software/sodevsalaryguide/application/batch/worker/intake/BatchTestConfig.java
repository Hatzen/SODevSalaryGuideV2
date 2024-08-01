package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import de.hartz.software.sodevsalaryguide.adapter.amqp.AMQPTestConfiguration;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceTestConfig;
import de.hartz.software.sodevsalaryguide.application.http.config.WiremockRouterServiceImpl;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Import({BatchConfiguration.class, PersistenceTestConfig.class, AMQPTestConfiguration.class})
public class BatchTestConfig {

    @Primary
    @Bean
    public RouterService routerService() {
        return new WiremockRouterServiceImpl();
    }
}
