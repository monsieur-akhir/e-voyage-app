package tech.dev.eVoyageBackend.configs;

import java.time.Duration;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import tech.dev.eVoyageBackend.jobs.NodeDto;
import tech.dev.eVoyageBackend.utils.ParamConfig;
import tech.dev.eVoyageBackend.utils.dto.UserDto;

@Configuration
@ComponentScan("tech.synelia.systemManagerOci")
public class RedisConfig {

	@Autowired
	private ParamConfig paramConfig;

	@Autowired
	private Environment environment;

	private final Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	// Configuration pour Jedis en mode standalone
	@Bean
	@Primary
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(
				paramConfig.getRedisHost(), paramConfig.getRedisPort());

		// Ajouter le mot de passe
		if (paramConfig.getRedisPassword() != null && !paramConfig.getRedisPassword().isEmpty()) {
			standaloneConfig.setPassword(paramConfig.getRedisPassword());
		}

		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
				.connectTimeout(Duration.ofSeconds(60)) // Timeout de 30 secondes
				.usePooling()
				.build();

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfig,
				jedisClientConfiguration);
		jedisConnectionFactory.setDatabase(1); // Sélection de la base de données 1

		slf4jLogger.info("Configuration de JedisConnectionFactory avec hôte: {} et port: {}",
				paramConfig.getRedisHost(), paramConfig.getRedisPort());

		return jedisConnectionFactory;
	}

	// Configuration pour Lettuce en mode standalone
	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(
				paramConfig.getRedisHost(), paramConfig.getRedisPort());

		// Ajouter le mot de passe
		if (paramConfig.getRedisPassword() != null && !paramConfig.getRedisPassword().isEmpty()) {
			standaloneConfig.setPassword(paramConfig.getRedisPassword());
		}

		LettuceClientConfiguration lettuceClientConfig = LettuceClientConfiguration.builder()
				.commandTimeout(Duration.ofSeconds(60)) // Timeout de 30 secondes
				.build();

		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(standaloneConfig,
				lettuceClientConfig);
		lettuceConnectionFactory.setDatabase(1); // Sélection de la base de données 1

		slf4jLogger.info("Configuration de LettuceConnectionFactory avec hôte: {} et port: {}",
				paramConfig.getRedisHost(), paramConfig.getRedisPort());

		return lettuceConnectionFactory;
	}

	// Template Redis pour UserDto
	@Bean
	public RedisTemplate<String, UserDto> redisTemplate() {
		final RedisTemplate<String, UserDto> template = new RedisTemplate<>();

		// Utiliser Jedis ou Lettuce selon le profil actif
		if (environment.getActiveProfiles() != null && environment.getActiveProfiles().length > 0) {
			String activeProfiles = String.join(",", environment.getActiveProfiles());
			slf4jLogger.info("Active profiles: " + activeProfiles);

			if (Arrays.stream(environment.getActiveProfiles())
					.anyMatch(env -> env.equals("local") || env.equals("development"))) {
				// Utilisation de Jedis en DEV/STAGING
				template.setConnectionFactory(jedisConnectionFactory());
			} else if (Arrays.stream(environment.getActiveProfiles())
					.anyMatch(env -> env.equals("staging") || env.equals("prod"))) {
				// Utilisation de Lettuce en PROD
				template.setConnectionFactory(lettuceConnectionFactory());
			}
		}

		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(UserDto.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserDto.class));

		return template;
	}

	// Template Redis pour NodeDto
	@Bean
	public RedisTemplate<String, NodeDto> nodeTemplate() {
		final RedisTemplate<String, NodeDto> template = new RedisTemplate<>();
		template.setConnectionFactory(lettuceConnectionFactory()); // Utilisation de Lettuce par défaut
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(NodeDto.class));

		return template;
	}
}
