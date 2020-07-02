package pl.devzyra.todospring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TaskRepository taskRep;


    @Test
    void httpGet_readOneTask() throws Exception {

            List<Task> taskList = taskRep.findAll();
            Long id = taskList.get(0).getId();

            mockMvc.perform(get("/tasks/"+ id)).andExpect(status().isOk());


    }
}