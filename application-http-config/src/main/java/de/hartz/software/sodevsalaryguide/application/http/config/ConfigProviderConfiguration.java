package de.hartz.software.sodevsalaryguide.application.http.config;

import de.hartz.software.sodevsalaryguide.application.http.config.env.DockerRouterServiceImpl;
import de.hartz.software.sodevsalaryguide.application.http.config.env.KubernetesRouterServiceImpl;
import de.hartz.software.sodevsalaryguide.application.http.config.env.LocalDeveloperRouterServiceImpl;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.security.config.Customizer.withDefaults;

public class ConfigProviderConfiguration {

    // @RefreshScope // Wont be useful as only deployment will change service..
    @Bean
    public RouterService routerService() {
        if (Files.exists(Path.of("/.dockerenv"))) {
            return new DockerRouterServiceImpl();
        } else if (Files.exists(Path.of("/var/run/secrets/kubernetes.io"))) {
            return new KubernetesRouterServiceImpl();
        }
        return new LocalDeveloperRouterServiceImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // block all requests without authenification to protect actuators etc.
        // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
        http
                .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }
    
}
