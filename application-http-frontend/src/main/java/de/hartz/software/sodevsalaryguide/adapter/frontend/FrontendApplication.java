package de.hartz.software.sodevsalaryguide.adapter.frontend;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({FrontendConfig.class})
public class FrontendApplication {

    public static void main(String[] args) {
        val config = SpringApplication.run(FrontendApplication.class, args);
    }

}
