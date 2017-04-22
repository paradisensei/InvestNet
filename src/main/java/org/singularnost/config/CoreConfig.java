package org.singularnost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aidar Shaifutdinov.
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "org.singularnost.service", "org.singularnost.util",
        "org.singularnost.task", "org.singularnost.logging"
})
public class CoreConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
