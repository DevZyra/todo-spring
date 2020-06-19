package pl.devzyra.todospring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("task")
public class TaskConfigProperties {

    private Template template;



    public static class Template{

        private Boolean allowMultipleTasks;

        public Boolean getAllowMultipleTasks() {
            return allowMultipleTasks;
        }

        public void setAllowMultipleTasks(Boolean allowMultipleTasks) {
            this.allowMultipleTasks = allowMultipleTasks;
        }
    }
}
