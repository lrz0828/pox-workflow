package com.sgai.pox.engine.core.redis;

import com.sgai.pox.engine.core.redis.aspect.components.RepeatRequestComponent;
import com.sgai.pox.engine.core.redis.redlock.RedissonDistributedLocker;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Configuration
@ConditionalOnProperty(name = "pox.redisson.enabled", havingValue = "true")
public class RedissonConfig {

    @Value("${pox.redisson.config}")
    private String redissonConfig;

    @Bean
    @SneakyThrows
    public RedissonClient redissonClient() {
        Config config = Config.fromJSON(redissonConfig);
        return Redisson.create(config);
    }

    @Bean
    public RedissonDistributedLocker redissonDistributedLocker() {
        return new RedissonDistributedLocker(redissonClient());
    }

    @Bean
    public RepeatRequestComponent repeatRequestComponent() {
        return new RepeatRequestComponent(redissonDistributedLocker());
    }
}
