package pl.devzyra.todospring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
// @Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class LoggerFilter implements Filter /* Ordered */{


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest ){
            var httpRequest = (HttpServletRequest) servletRequest;
            log.info("[doFilter]" + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

 /*    @Override
       public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
                              }   */
}
