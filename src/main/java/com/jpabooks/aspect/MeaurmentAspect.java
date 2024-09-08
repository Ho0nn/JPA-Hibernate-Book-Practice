package com.jpabooks.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Order(1)
@Component
public class MeaurmentAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "execution(* com.jpabooks.service..*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder("KPI: ");
        sb.append("[").append(joinPoint.getKind()).append("] for: ")
                .append(joinPoint.getSignature()).append(" withArgs: ")
                .append("(").append(StringUtils.arrayToDelimitedString(joinPoint.getArgs(), ","))
                .append(")");

        Object returnValue = joinPoint.proceed();

        sb.append(" took: ").append(System.currentTimeMillis() - startTime).append(" ms.");
        log.info(sb.toString());

        return returnValue;
    }
}
