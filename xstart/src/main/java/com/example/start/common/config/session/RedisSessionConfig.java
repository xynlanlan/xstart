package com.example.start.common.config.session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//默认是1800秒过期
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisSessionConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.passwd}")
    private String redisPasswd;

    @Value("${spring.redis.timeOut}")
    private int timeOut = 2000;

    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName(redisHost);
        cf.setPort(redisPort);
        cf.setPassword(redisPasswd);
        cf.setTimeout(timeOut);

        cf.afterPropertiesSet();
        return cf;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> rt = new RedisTemplate<String, Object>();
        rt.setConnectionFactory(redisConnectionFactory());
        return rt;
    }
}
