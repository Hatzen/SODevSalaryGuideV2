package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import de.hartz.software.sodevsalaryguide.adapter.frontend.FrontendConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.application.http.api.HttpApiConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    FrontendConfig.class,
    HttpApiConfiguration.class,
    PersistenceConfiguration.class
})
public class HttpApiTestConfiguration {}
