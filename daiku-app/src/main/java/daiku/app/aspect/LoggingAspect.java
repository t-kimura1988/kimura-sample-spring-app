package daiku.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* *..*.*Controller.*(..))")
    public void beforeController(JoinPoint joinPoint) {
        if(log.isInfoEnabled()) {
            Object[] arguments = joinPoint.getArgs();
            for(Object obj: arguments) {
                log.info("[AOP] arguments: " + obj);
            }
        }
    }
}
