package com.sgai.pox.admin.security.component.authorization;

import com.sgai.pox.admin.security.component.authorization.controller.TokenController;
import com.sgai.pox.admin.security.component.authorization.listener.AuthenticationSuccessEventListener;
import com.sgai.pox.admin.security.service.RedisAuthorizationCodeServices;
import com.sgai.pox.admin.security.service.RedisClientDetailsService;
import com.sgai.pox.admin.security.service.UserDetailsServiceImpl;
import com.sgai.pox.engine.common.core.constant.CacheConstants;
import com.sgai.pox.engine.common.core.security.SecurityUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pox
 * @date 2020年6月23日
 */
public class PoxAuthorizationServerAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix="pox.security.config")
    public PoxSecurityProperties poxSecurityProperties(){
        return new PoxSecurityProperties();
    }

    @Bean
    public TokenController tokenController(){
        return new TokenController();
    }

    @Bean
    @ConditionalOnMissingBean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisClientDetailsService redisClientDetailsService(DataSource dataSource, RedisTemplate redisTemplate) {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        clientDetailsService.setRedisTemplate(redisTemplate);
        return clientDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService(JdbcClientDetailsService redisClientDetailsService){
        return new UserDetailsServiceImpl(redisClientDetailsService);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(RedisTemplate redisTemplate) {
        RedisAuthorizationCodeServices redisAuthorizationCodeServices = new RedisAuthorizationCodeServices();
        redisAuthorizationCodeServices.setRedisTemplate(redisTemplate);
        return redisAuthorizationCodeServices;
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken && authentication.getUserAuthentication() != null) {
                final Map<String, Object> tokenEnhancerInfo = new HashMap<>(16);
                SecurityUser securityUser = (SecurityUser) authentication.getUserAuthentication().getPrincipal();
                tokenEnhancerInfo.put("userRealName", securityUser.getUserRealName());
                tokenEnhancerInfo.put("orgId", securityUser.getOrgId());
                tokenEnhancerInfo.put("roleId", securityUser.getRoleId());
                tokenEnhancerInfo.put("additionalInformation", securityUser.getAdditionalInformation());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(tokenEnhancerInfo);
            }
            return accessToken;
        };
    }

    @Bean
    public AuthenticationSuccessEventListener authenticationSuccessEventListener(){
        return new AuthenticationSuccessEventListener();
    }
}
