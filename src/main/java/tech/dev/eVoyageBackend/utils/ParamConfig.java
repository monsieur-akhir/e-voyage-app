package tech.dev.eVoyageBackend.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ParamConfig {
	// les parametres de SMTP

	@Value("#{'${redis.nodes}'.split(',')}")
	private List<String> nodes;

	@Value("${redis.clusterName}")
	private String clusterName;

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private Integer redisPort;

	@Value("${redis.password}")
	private String redisPassword;

}
