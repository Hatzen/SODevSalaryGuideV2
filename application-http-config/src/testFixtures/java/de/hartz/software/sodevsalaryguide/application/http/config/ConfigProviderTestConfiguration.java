package de.hartz.software.sodevsalaryguide.application.http.config;

import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ConfigProviderTestConfiguration {

    @Primary
    @Bean
    public RouterService routerService() {
        return new WiremockRouterServiceImpl();
    }

}
