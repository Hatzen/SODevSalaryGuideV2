package de.hartz.software.sodevsalaryguide.application.http.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.hartz.software.sodevsalaryguide.adapater.amqp.RabbitMQConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.application.http.api.helper.RangeDeserializer;
import de.hartz.software.sodevsalaryguide.application.http.config.ConfigProviderConfiguration;
import de.hartz.software.sodevsalaryguide.core.model.Range;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableAutoConfiguration
@EnableWebMvc
@EnableCaching // https://github.com/MarcGiffing/bucket4j-spring-boot-starter/issues/24
@Configuration
@ComponentScan
// TODO: Why is the autoconfiguration not detected?? only in test? Which would be expected..
@Import({PersistenceConfiguration.class, RabbitMQConfig.class, ConfigProviderConfiguration.class})
public class HttpApiConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(HttpApiConfiguration.class);

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // https://stackoverflow.com/a/55571427/8524651
        HttpMessageConverter<?> jacksonConverter =
                converters.stream()
                        .filter(converter -> converter.getClass().equals(MappingJackson2HttpMessageConverter.class))
                        .findFirst().orElseThrow(RuntimeException::new);

        // Be careful the bean is not identical as we cannot inject it here.
        val objectMapper = objectMapper();
        converters.add(converters.indexOf(jacksonConverter), new MappingJackson2HttpMessageConverter(objectMapper));
    }

    // TODO: Currently not used as we need to overwrite MessageConverter https://stackoverflow.com/questions/49390931/why-is-spring-boot-not-using-a-primary-jackson-objectmapper-for-json-serializat
    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Range.class, new RangeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/v1/*");
    }


}
