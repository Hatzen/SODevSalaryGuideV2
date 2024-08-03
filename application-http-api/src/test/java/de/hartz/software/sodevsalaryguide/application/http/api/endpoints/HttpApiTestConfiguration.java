package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.application.http.api.HttpApiConfiguration;
import de.hartz.software.sodevsalaryguide.application.http.config.ConfigProviderTestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HttpApiConfiguration.class,
        PersistenceConfiguration.class,
        ConfigProviderTestConfiguration.class
})
public class HttpApiTestConfiguration {
}
