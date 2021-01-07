package com.sgai.pox.admin.security.annotation;

import com.sgai.pox.admin.security.component.authorization.PoxAuthorizationServerAutoConfiguration;
import com.sgai.pox.admin.security.component.authorization.PoxAuthorizationServerBeanDefinitionRegistrar;
import com.sgai.pox.admin.security.component.authorization.PoxSecurityConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Documented
@Inherited
@EnableAuthorizationServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({PoxAuthorizationServerAutoConfiguration.class, PoxSecurityConfig.class,
        PoxAuthorizationServerBeanDefinitionRegistrar.class})
public @interface EnablePoxAuthorizationServer {
}
