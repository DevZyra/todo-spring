package pl.devzyra.todospring.controller;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.devzyra.todospring.config.TaskConfigProperties;


@RestController
public class InfoController {


    private final DataSourceProperties dataSourceProperties;
    private final TaskConfigProperties taskConfigProperties;

    public InfoController(DataSourceProperties dataSourceProperties, TaskConfigProperties taskConfigProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.taskConfigProperties = taskConfigProperties;
    }

    @GetMapping("/info/url")
    String url(){
        return dataSourceProperties.getUrl();
    }

    @GetMapping("/info/prop")
    Boolean myProp(){
        return taskConfigProperties.getTemplate().getAllowMultipleTasks();
    }


}
