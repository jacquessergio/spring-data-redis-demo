package com.example.RedisDemo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JedisConfig {

	// @Value("${spring.redis.host}")
	// private String host;
	//
	// @Value("${spring.redis.port}")
	// private Integer port;
	//
	// @Value("${spring.redis.database}")
	// private Integer database;
	//
	// @Value("${spring.redis.ssl}")
	// private Boolean hasSSL;
	//
	// @Value("${spring.redis.password}")
	// private String password;

	@Autowired
	@Qualifier("SSM")
	private String jsonProperty;

	// @Bean
	// public JedisConnectionFactory jedisConnectionFactory() {
	//
	// final RedisStandaloneConfiguration redisStandaloneConfiguration = new
	// RedisStandaloneConfiguration();
	// redisStandaloneConfiguration.setHostName(host);
	// redisStandaloneConfiguration.setPort(port);
	// redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
	// redisStandaloneConfiguration.setDatabase(database);
	//
	// final JedisClientConfigurationBuilder jedisClientConfiguration =
	// JedisClientConfiguration.builder();
	// jedisClientConfiguration.useSsl();
	// jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60)); // 60s
	// connection timeout
	//
	// final JedisConnectionFactory factory = new
	// JedisConnectionFactory(redisStandaloneConfiguration,
	// jedisClientConfiguration.build());
	// //final JedisConnectionFactory factory = new
	// JedisConnectionFactory(redisStandaloneConfiguration);
	// //factory.setUseSsl(hasSSL);
	//
	// return factory;
	//
	// }

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {

		final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

		try {

			final JsonNode jsonNode = new ObjectMapper().readTree(jsonProperty);

			redisStandaloneConfiguration.setHostName(jsonNode.get("host").asText());
			redisStandaloneConfiguration.setPort(jsonNode.get("port").asInt());
			redisStandaloneConfiguration.setPassword(jsonNode.get("password").asText());
			redisStandaloneConfiguration.setDatabase(jsonNode.get("database").asInt());

			final JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
			jedisClientConfiguration.useSsl();
			jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));

			final JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
					jedisClientConfiguration.build());

			return factory;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;

	}

}
