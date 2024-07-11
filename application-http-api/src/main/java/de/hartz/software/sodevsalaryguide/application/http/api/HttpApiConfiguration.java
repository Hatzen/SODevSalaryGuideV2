package de.hartz.software.sodevsalaryguide.application.http.api;

import de.hartz.software.sodevsalaryguide.adapater.amqp.RabbitMQConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import de.hartz.software.sodevsalaryguide.core.service.RouterServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan
// TODO: Why is the autoconfiguration not detected?? only in test? Which would be expected..
@Import({PersistenceConfiguration.class, RabbitMQConfig.class})
public class HttpApiConfiguration {

    @Bean
    public RouterService routerService() {
        return new RouterServiceImpl();
    }

}
