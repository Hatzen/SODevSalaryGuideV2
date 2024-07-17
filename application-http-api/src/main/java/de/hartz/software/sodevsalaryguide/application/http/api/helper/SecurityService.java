package de.hartz.software.sodevsalaryguide.application.http.api.helper;

import org.springframework.stereotype.Service;

/**
 * You can define custom beans like the SecurityService which can be used in the SpEl expressions.
 **/
// TODO: evaluate and improve
// https://docs.spring.io/spring-security/site/docs/5.2.0.RELEASE/reference/html/protection-against-exploits.html
@Service
public class SecurityService {

    public String username() {
        // TODO: Setup proper context.
        // String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = "anonymousUser";
        if (name.equals("anonymousUser")) {
            return null;
        }
        return name;
    }

    public boolean notSignedIn() {
        return true;
    }

}