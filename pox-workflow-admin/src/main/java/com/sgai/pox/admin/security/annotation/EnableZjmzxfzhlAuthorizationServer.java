package com.sgai.pox.admin.security.annotation;

import com.sgai.pox.admin.security.component.authorization.ZjmzxfzhlAuthorizationServerAutoConfiguration;
import com.sgai.pox.admin.security.component.authorization.ZjmzxfzhlAuthorizationServerBeanDefinitionRegistrar;
import com.sgai.pox.admin.security.component.authorization.ZjmzxfzhlSecurityConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 庄金明
 * @date 2020年7月18日
 */
@Documented
@Inherited
@EnableAuthorizationServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ZjmzxfzhlAuthorizationServerAutoConfiguration.class, ZjmzxfzhlSecurityConfig.class,
        ZjmzxfzhlAuthorizationServerBeanDefinitionRegistrar.class})
public @interface EnableZjmzxfzhlAuthorizationServer {
}
