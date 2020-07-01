package pl.devzyra.todospring.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.repositories.TaskRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    TaskRepository taskRepo;

    @Test
    void httpGet_readTasks(){

            // given
        taskRepo.save(Task.builder().description("Lombok builder is").deadline(LocalDateTime.now()).build());
        taskRepo.save(Task.builder().description("Very f... usefull").deadline(LocalDateTime.now()).build());

            // when

           Task[] res = testRestTemplate.getForObject("http://localhost:"+ port + "/tasks",Task[].class);

            // then
            assertThat(res).isNotEmpty();


    }
}