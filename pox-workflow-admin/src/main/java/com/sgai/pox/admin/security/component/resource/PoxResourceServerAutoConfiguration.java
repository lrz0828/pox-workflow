package com.sgai.pox.admin.security.component.resource;

import com.sgai.pox.admin.security.service.ElPermissionService;
import com.sgai.pox.admin.security.service.RedisClientDetailsService;
import com.sgai.pox.engine.common.core.constant.CacheConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author pox
 * @date 2021年01月04日
 */
public class PoxResourceServerAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix="pox.resource.config")
    public PoxResourceProperties poxResourceProperties(){
        return new PoxResourceProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisClientDetailsService redisClientDetailsService(DataSource dataSource, RedisTemplate<String, Object> redisTemplate) {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        clientDetailsService.setRedisTemplate(redisTemplate);
        return clientDetailsService;
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }

    @Bean
    public ElPermissionService elp(){
        return new ElPermissionService();
    }
}
