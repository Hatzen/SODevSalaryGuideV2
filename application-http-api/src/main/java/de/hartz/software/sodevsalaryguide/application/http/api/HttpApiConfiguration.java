package de.hartz.software.sodevsalaryguide.application.http.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.hartz.software.sodevsalaryguide.adapater.amqp.RabbitMQConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.application.http.api.helper.RangeDeserializer;
import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import de.hartz.software.sodevsalaryguide.core.service.RouterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan
// TODO: Why is the autoconfiguration not detected?? only in test? Which would be expected..
@Import({PersistenceConfiguration.class, RabbitMQConfig.class})
public class HttpApiConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HttpApiConfiguration.class);

    @Bean
    public RouterService routerService() {
        return new RouterServiceImpl();
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Range.class, new RangeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    /*
    wont work as cycle dependency for rabbitmq
    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public SimpleModule module() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Range.class, new RangeDeserializer());
        objectMapper.registerModule(module);
        return module;
    }
     */

}
