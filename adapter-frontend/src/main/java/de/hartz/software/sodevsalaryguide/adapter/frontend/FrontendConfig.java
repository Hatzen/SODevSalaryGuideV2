package de.hartz.software.sodevsalaryguide.adapter.frontend;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Log4j2
@EnableAutoConfiguration
@AutoConfiguration
public class FrontendConfig implements WebMvcConfigurer {
    @Autowired
    ResourceLoader resourceLoader;

    @SneakyThrows
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO: copy resources to applications resources or load resource relative to this classes path
        // val test = FrontendConfig.class.getClassLoader().getResource("public/");
        // resourceLoader.getResource(test.openStream());
        // registry.addResourceHandler("/app/**").addResourceLocations(test);
        // .addResourceLocations("classpath:/public/");

        val test = FrontendConfig.class.getClassLoader().getResource("public/");
        if (test != null) {
            val filePath = test.getFile();

            log.error("{}", filePath);
            registry.addResourceHandler("/app/**").addResourceLocations(filePath);

        } else {

            log.error("test == null");
            registry.addResourceHandler("/app/**").addResourceLocations("classpath:/public/");
        }

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/app/").setViewName("forward:/app/index.html");
    }
}
