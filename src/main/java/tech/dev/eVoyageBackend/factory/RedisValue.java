package tech.dev.eVoyageBackend.factory;

public interface RedisValue<T> {

	void Re();

	T get(String key);

	boolean save(String key, T value, boolean isDelay);

	void delete(String key);

	boolean supports(String s);
}
