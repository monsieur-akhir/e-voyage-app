package tech.dev.eVoyageBackend.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.UsersDto;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenService {

    // Lire les durées d'expiration depuis application.properties ou application.yml
    @Value("${token.access-token.expiration}")
    private long accessTokenExpiration;

    @Value("${token.refresh-token.expiration}")
    private long refreshTokenExpiration;

    // Lire la clé secrète depuis application.properties ou application.yml
    @Value("${token.secret-key}")
    private String secretKey;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * Générer un token d'accès.
     */
    public String generateAccessToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("roleId", user.getRoleId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Générer un refresh token.
     */
    public String generateRefreshToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Stocker les tokens dans Redis.
     */
    public void storeTokensInRedis(Long userId, String accessToken, String refreshToken) {
        try {
            redisTemplate.opsForValue().set("accessToken:" + userId, accessToken, accessTokenExpiration, TimeUnit.MILLISECONDS);
            redisTemplate.opsForValue().set("refreshToken:" + userId, refreshToken, refreshTokenExpiration, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde des tokens dans Redis : " + e.getMessage());
        }
    }

    /**
     * Récupérer le refresh token depuis Redis.
     */
    public String getRefreshTokenFromRedis(Long userId) {
        try {
            return redisTemplate.opsForValue().get("refreshToken:" + userId);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du refresh token dans Redis : " + e.getMessage());
            return null;
        }
    }

    /**
     * Supprimer les tokens de Redis.
     */
    public void removeTokensFromRedis(Long userId) {
        try {
            redisTemplate.delete("accessToken:" + userId);
            redisTemplate.delete("refreshToken:" + userId);
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression des tokens dans Redis : " + e.getMessage());
        }
    }

    /**
     * Vérifier si l'utilisateur est connecté via son token.
     */
    public boolean isUserConnected(Long userId) {
        try {
            String accessTokenKey = "accessToken:" + userId;
            String refreshTokenKey = "refreshToken:" + userId;

            boolean accessTokenExists = Boolean.TRUE.equals(redisTemplate.hasKey(accessTokenKey));
            boolean refreshTokenExists = Boolean.TRUE.equals(redisTemplate.hasKey(refreshTokenKey));

            return accessTokenExists || refreshTokenExists;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de connexion utilisateur : " + e.getMessage());
            return false;
        }
    }

    /**
     * Vérifier la validité du token JWT.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("Token expiré : " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Token non supporté : " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Token mal formé : " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Signature invalide : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Token vide : " + e.getMessage());
        }
        return false;
    }

    /**
     * Récupérer les informations utilisateur depuis le token JWT.
     */
    public UsersDto getUserFromToken(String token) {
        try {
            var claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            UsersDto usersDto = new UsersDto();
            usersDto.setId(Integer.valueOf(claims.get("userId").toString()));
            usersDto.setRoleId(Integer.valueOf(claims.get("roleId").toString()));
            usersDto.setEmail(claims.getSubject());
            usersDto.setStatus("ACTIVE"); // Vous pouvez le récupérer de la base si nécessaire

            return usersDto;
        } catch (Exception e) {
            System.err.println("Erreur lors de l'extraction du token : " + e.getMessage());
            return null;
        }
    }

}
