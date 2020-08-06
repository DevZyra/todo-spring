package pl.devzyra.todospring.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.devzyra.todospring.model.Task;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DataLoad implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Task.builder().description("Bootstraped task").deadline(LocalDateTime.of(1992, Month.MARCH,26,20,30)).build();


    }

}
