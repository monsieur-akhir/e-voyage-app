package tech.dev.eVoyageBackend.rest;

import com.zaxxer.hikari.HikariDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ConnectionInterceptor implements HandlerInterceptor {

    private final HikariDataSource dataSource;

    public ConnectionInterceptor(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (dataSource.getHikariPoolMXBean() != null) {
            dataSource.getHikariPoolMXBean().softEvictConnections();
            System.out.println("Connexion dormantes fermées après l'appel du service : " + request.getRequestURI());
        }
    }

}
