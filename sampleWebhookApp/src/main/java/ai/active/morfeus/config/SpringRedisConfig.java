package ai.active.morfeus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SpringRedisConfig {

  @Value("${REDIS_PORT:6379}") private int redisPort;

  @Value("${REDIS_DATABASE:0}") private int dataBase;

  @Value("${REDIS_HOST:localhost}") private String redisHost;

  @Bean
  public JedisConnectionFactory connectionFactory() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName(redisHost);
    connectionFactory.setPort(redisPort);
    connectionFactory.setDatabase(dataBase);
    return connectionFactory;
  }

  @Bean RedisTemplate<String, String> redisTemplate() {
    final RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return template;
  }
}
