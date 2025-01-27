package tech.dev.eVoyageBackend.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import tech.dev.eVoyageBackend.factory.RedisEnum;
import tech.dev.eVoyageBackend.factory.RedisValue;
import tech.dev.eVoyageBackend.jobs.NodeDto;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



@Component
public class Node implements RedisValue<tech.dev.eVoyageBackend.jobs.NodeDto> {

	@Autowired
	private RedisTemplate<String, tech.dev.eVoyageBackend.jobs.NodeDto> nodeTemplate;

	@Override
	public tech.dev.eVoyageBackend.jobs.NodeDto get(String key) {
		try {
			return nodeTemplate.opsForValue().get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Set<String> getKeys(String pattern) {
		try {
			return nodeTemplate.keys(pattern + "*");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<tech.dev.eVoyageBackend.jobs.NodeDto> getClusterNodes(String pattern) {
		try {
			return nodeTemplate.opsForValue().multiGet(this.getKeys(pattern));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean supports(String s) {
		return RedisEnum.NODE.name().equals(s);
	}

	@Override
	public void delete(String key) {
		nodeTemplate.delete(key);
	}

	@Override
	public void Re() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean save(String key, tech.dev.eVoyageBackend.jobs.NodeDto value, boolean isDelay) {
		try {
			if (isDelay) {
				nodeTemplate.opsForValue().set(key, (NodeDto) value, 5, TimeUnit.MINUTES);
				return true;
			}else {
				nodeTemplate.opsForValue().set(key, value);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
