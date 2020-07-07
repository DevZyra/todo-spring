package pl.devzyra.todospring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

            // Set<HandlerInterceptor> interceptors and later on interceptors.forEach(registry::addInterceptor)
                     private final Interceptor interceptor;

                     public WebMvcConfig(Interceptor interceptor) {
                         this.interceptor = interceptor;
                     }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
