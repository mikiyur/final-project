package com.intentsg.service.tour.aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TourAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(pointcut = "execution(* com.intentsg.service.tour.controller.TourController.*(..))", returning = "returnedObject")
    public void AfterReturning(JoinPoint joinPoint, Object returnedObject){
        logger.info("method: {} returned object:{}", joinPoint.toString() , returnedObject);
    }

    @AfterReturning(pointcut = "execution(* com.intentsg.service.tour.service.TourService.*(..))", returning = "returnedObject")
    public void AfterReturningTourService(JoinPoint joinPoint, Object returnedObject){
        logger.info("method: {} returned object:{}", joinPoint.toString() , returnedObject);
    }

    @AfterReturning(pointcut = "execution(* com.intentsg.service.tour.service.TourService.*(..))", returning = "returnedObject")
    public void AfterReturningTourServiceImpl(JoinPoint joinPoint, Object returnedObject){
        logger.info("method: {} returned object:{}", joinPoint.toString() , returnedObject);
    }
}
