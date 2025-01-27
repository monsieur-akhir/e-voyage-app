package tech.dev.eVoyageBackend.utils;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.ParametreGenerauxBusiness;
import tech.dev.eVoyageBackend.dao.entity.ApiUser;
import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.dao.repository.ApiUserRepository;
import tech.dev.eVoyageBackend.dao.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Log
@Service
public class FonctionsUtiles {

    @Autowired private UserRepository userRepository;
    @Autowired private ApiUserRepository apiUserRepository;

    @Autowired
    private ParametreGenerauxBusiness parametreService;

    private final Logger slf4jLogger = LoggerFactory.getLogger(getClass());

    public void tokenExpiration(String token, Integer userId, Locale locale) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (userId == null) {
            String goodToken= "";
            if(token.startsWith("Bearer")){
                goodToken = token.split(" ")[1];
            }else{
                goodToken = token;
            }
        
            System.out.println(goodToken);

            List<ApiUser> usersFind = apiUserRepository.findByToken(goodToken, false);
            if (usersFind.isEmpty()) {
                throw new InvalidTokenException("Token invalide");
            }

            LocalDateTime tokenExpireAt = usersFind.get(0).getTokenExpireAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            if (currentTime.isAfter(tokenExpireAt)) {
                throw new SessionExpiredException("Session expirée");
            } else {
                System.out.println("Le token est encore valide.");
            }
        } else {
            List<User> usersFind = userRepository.findByToken(token, false);
            if (usersFind.isEmpty()) {
                throw new InvalidTokenException("Token invalide");
            }

            LocalDateTime tokenExpireAt = usersFind.get(0).getTokenExpireAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            if (currentTime.isAfter(tokenExpireAt)) {
                throw new SessionExpiredException("Session expirée");
            } else {
                System.out.println("Le token est encore valide.");
            }
        }
    }


// Méthode surchargée sans le paramètre `type`
    public void tokenExpiration(String token, Locale locale) {
        // Appel de la méthode principale avec un type par défaut
        tokenExpiration(token, null, locale);
    }

    public class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message) {
            super(message);
        }
    }
    
    public class SessionExpiredException extends RuntimeException {
        public SessionExpiredException(String message) {
            super(message);
        }
    }





    // Méthode qui retourne un boolean pour vérifier si le token est valide
    public boolean isTokenValid(String token, Integer userId, Locale locale) {
        LocalDateTime currentTime = LocalDateTime.now();

        try {
            if (userId == null) {
                String goodToken = token.startsWith("Bearer") ? token.split(" ")[1] : token;
                System.out.println(goodToken);

                List<ApiUser> usersFind = apiUserRepository.findByToken(goodToken, false);
                if (usersFind.isEmpty()) {
                    throw new InvalidTokenException("Token invalide");
                }

                LocalDateTime tokenExpireAt = usersFind.get(0).getTokenExpireAt()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                if (currentTime.isAfter(tokenExpireAt)) {
                    throw new SessionExpiredException("Session expirée");
                } else {
                    System.out.println("Le token est encore valide.");
                }
            } else {
                List<User> usersFind = userRepository.findByToken(token, false);
                if (usersFind.isEmpty()) {
                    throw new InvalidTokenException("Token invalide");
                }

                LocalDateTime tokenExpireAt = usersFind.get(0).getTokenExpireAt()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                if (currentTime.isAfter(tokenExpireAt)) {
                    throw new SessionExpiredException("Session expirée");
                } else {
                    System.out.println("Le token est encore valide.");
                }
            }
        } catch (InvalidTokenException | SessionExpiredException e) {
            System.out.println("Erreur de validation du token : " + e.getMessage());
            return false;
        }

        return true;
    }

    // Méthode surchargée sans le paramètre `userId`
    public boolean isTokenValid(String token, Locale locale) {
        return isTokenValid(token, null, locale);
    }


}
