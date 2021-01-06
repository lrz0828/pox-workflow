package com.sgai.pox.admin.security.annotation;

import com.sgai.pox.admin.security.component.resource.ZjmzxfzhlResourceServerAutoConfiguration;
import com.sgai.pox.admin.security.component.resource.ZjmzxfzhlResourceServerBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

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
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ZjmzxfzhlResourceServerAutoConfiguration.class, ZjmzxfzhlResourceServerBeanDefinitionRegistrar.class})
public @interface EnableZjmzxfzhlResourceServer {
}
