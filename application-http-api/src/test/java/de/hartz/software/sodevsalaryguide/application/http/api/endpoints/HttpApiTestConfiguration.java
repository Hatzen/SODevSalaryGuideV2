package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.application.http.api.HttpApiConfiguration;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import de.hartz.software.sodevsalaryguide.core.service.WiremockRouterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import({
        HttpApiConfiguration.class,
        PersistenceConfiguration.class
})
public class HttpApiTestConfiguration {

    @Primary
    @Bean
    public RouterService testRouterService() {
        return new WiremockRouterServiceImpl();
    }
}
