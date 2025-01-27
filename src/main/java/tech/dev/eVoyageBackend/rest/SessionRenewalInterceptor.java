package tech.dev.eVoyageBackend.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import tech.dev.eVoyageBackend.utils.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class SessionRenewalInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, UserDto> redisUserTemplate;

    // Seuil en secondes pour prolonger la session (ici, 5 minutes)
    private static final long RENEWAL_THRESHOLD_SECONDS = 300;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionKey = getSessionKeyFromRequest(request);

        if (sessionKey != null && redisUserTemplate.hasKey(sessionKey)) {
            Long ttl = redisUserTemplate.getExpire(sessionKey, TimeUnit.SECONDS);

            // Prolonge la session si le TTL est inférieur au seuil
            if (ttl != null && ttl > 0 && ttl < RENEWAL_THRESHOLD_SECONDS) {
                renewSession(sessionKey);
            }
        }

        return true;
    }

    private void renewSession(String key) {
        try {
            // Prolonge le TTL de la session de 40 minutes supplémentaires
            redisUserTemplate.expire(key, 40, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSessionKeyFromRequest(HttpServletRequest request) {
        // Extraire la clé de session depuis le cookie JSESSIONID
        String sessionKey = null;
        if (request.getCookies() != null) {
            for (javax.servlet.http.Cookie cookie : request.getCookies()) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    sessionKey = cookie.getValue();
                    break;
                }
            }
        }

        // Vous pouvez aussi essayer de récupérer depuis un header si nécessaire
        if (sessionKey == null) {
            sessionKey = request.getHeader("Session-Key");
        }

        return sessionKey;
    }

}
