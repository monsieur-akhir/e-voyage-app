package tech.dev.eVoyageBackend.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.dev.eVoyageBackend.factory.RedisValue;

@Component
public class RedisFactory {

	private final List<tech.dev.eVoyageBackend.factory.RedisValue> serviceList;

	@Autowired
	public RedisFactory(List<tech.dev.eVoyageBackend.factory.RedisValue> serviceList) {
		this.serviceList = serviceList;
	}

	public RedisValue get(String s) {
		return serviceList.stream().filter(service -> service.supports(s)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
