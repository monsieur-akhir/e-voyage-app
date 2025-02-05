package tech.dev.eVoyageBackend.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.UsersDto;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    @Value("${token.access-token.expiration}")
    private long accessTokenExpiration;

    @Value("${token.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Value("${token.secret-key}")
    private String secretKey;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Générer un token d'accès.
     */
    public String generateAccessToken(Users user) {
        log.info("Génération du token d'accès pour l'utilisateur ID: {}", user.getId());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("roleId", user.getRoleId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Générer un refresh token.
     */
    public String generateRefreshToken(Users user) {
        log.info("Génération du refresh token pour l'utilisateur ID: {}", user.getId());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("roleId", user.getRoleId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Stocker les tokens dans Redis.
     */
    public void storeTokensInRedis(Long userId, String accessToken, String refreshToken) {
        try {
            if (accessToken != null && refreshToken != null) {
                redisTemplate.opsForValue().set("accessToken:" + userId, accessToken, accessTokenExpiration, TimeUnit.MILLISECONDS);
                redisTemplate.opsForValue().set("refreshToken:" + userId, refreshToken, refreshTokenExpiration, TimeUnit.MILLISECONDS);
                log.info("Tokens stockés dans Redis pour l'utilisateur ID: {}", userId);
            } else {
                log.warn("Tentative de stockage d'un token NULL pour l'utilisateur ID: {}", userId);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la sauvegarde des tokens dans Redis : {}", e.getMessage(), e);
        }
    }

    /**
     * Récupérer le refresh token depuis Redis.
     */
    public String getRefreshTokenFromRedis(Long userId) {
        try {
            return redisTemplate.opsForValue().get("refreshToken:" + userId);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du refresh token dans Redis : {}", e.getMessage(), e);
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
            log.info("Tokens supprimés de Redis pour l'utilisateur ID: {}", userId);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression des tokens dans Redis : {}", e.getMessage(), e);
        }
    }

    /**
     * Vérifier si l'utilisateur est connecté via son token.
     */
    public boolean isUserConnected(Long userId) {
        try {
            boolean accessTokenExists = Boolean.TRUE.equals(redisTemplate.hasKey("accessToken:" + userId));
            boolean refreshTokenExists = Boolean.TRUE.equals(redisTemplate.hasKey("refreshToken:" + userId));
            return accessTokenExists || refreshTokenExists;
        } catch (Exception e) {
            log.error("Erreur lors de la vérification de connexion utilisateur : {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * Vérifier la validité du token JWT.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expiré : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Token non supporté : {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Token mal formé : {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Signature invalide : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Token vide ou null : {}", e.getMessage());
        }
        return false;
    }

    /**
     * Récupérer les informations utilisateur depuis le token JWT.
     */
    public UsersDto getUserFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            UsersDto usersDto = new UsersDto();
            usersDto.setId(Integer.valueOf(claims.get("userId").toString()));
            usersDto.setRoleId(Integer.valueOf(claims.get("roleId").toString()));
            usersDto.setEmail(claims.getSubject());
            usersDto.setStatus("ACTIVE");

            return usersDto;
        } catch (JwtException e) {
            log.error("Erreur lors de l'extraction des données du token : {}", e.getMessage(), e);
            return null;
        }
    }
}
