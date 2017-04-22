package org.singularnost.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
@Aspect
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @After("execution(public void org.singularnost.controller.api.*.*(..))")
    public void logOAuth(JoinPoint jp) {
        logger.info("Controller method invoked");
    }

}
