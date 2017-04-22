package org.singularnost.config;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Aidar Shaifutdinov.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan("org.singularnost.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/templates/");
        configurer.setDefaultEncoding("UTF-8");
        configurer.setFreemarkerSettings(new Properties() {{
            this.put("default_encoding", "UTF-8");
        }});
        return configurer;
    }

    @Bean
    @Autowired
    public freemarker.template.Configuration freeMarkerConfiguration(
            FreeMarkerConfig configurer) {
        return configurer.getConfiguration();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/*")
                .addResourceLocations("/resources/js/")
                .setCachePeriod(86400);
        registry.addResourceHandler("/css/*")
                .addResourceLocations("/resources/css/")
                .setCachePeriod(86400);
    }

}
