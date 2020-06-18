package pl.devzyra.todospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.Validator;

@Configuration
public class ValidationConfig  /*implements RepositoryRestConfigurer */ {

    @Bean
/*    LocalValidatorFactoryBean*/ Validator validator(){
        return new LocalValidatorFactoryBean();
    }

 /*   @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate",validator());
        validatingListener.addValidator("beforeSave",validator());
    }*/
}
