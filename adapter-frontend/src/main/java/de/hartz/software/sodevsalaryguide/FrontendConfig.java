package de.hartz.software.sodevsalaryguide;

import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@EnableAutoConfiguration
@Configuration
@RequiredArgsConstructor
class FrontendConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/app/**")
                .addResourceLocations("classpath:/public/");

    //.setCachePeriod(3600)
                //.resourceChain(true)
                //.addResolver(new EncodedResourceResolver());;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/app/").setViewName("forward:/app/index.html");
    }

/*
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(someFilter());
        registration.addUrlPatterns("/app/");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }

    public Filter someFilter() {
        return new CharacterEncodingFilter();
    }*/
}

/**
 * Sets character encoding on the request and response.
 *
 *  https://stackoverflow.com/a/28123040/8524651
 * @author gaurav
 */
class CharacterEncodingFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            response.setCharacterEncoding("UTF-8");
        } finally {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }
}

// https://stackoverflow.com/a/51264605/8524651
class EncodingFilter extends GenericFilterBean {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }
}