package pl.devzyra.todospring.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class LogicAspect {


    private final Timer timer;

    int count = 0;

    public LogicAspect(MeterRegistry registry) {
        this.timer = registry.timer("pl.devzyra.timer");
    }

    @Pointcut("execution(* pl.devzyra.todospring.services.TaskService.findAll())")
    void pointcut(){
    }


    @Before("pointcut()")
    void logMethodCall(JoinPoint jp){
        log.info( "@Before "+"{}" , jp.getSignature().getName() +" ~~~ " + jp.getArgs());
    }


    @Around("pointcut()")
    Object aroundProjectGroup(ProceedingJoinPoint jp) throws Throwable {
        count++;
        log.info( "Ilość wywołań aspektu " + count);

        return timer.record(()->{
            try{
              return jp.proceed();
            }catch (Throwable e){
                if (e instanceof RuntimeException){
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            }
        });


    }

}
