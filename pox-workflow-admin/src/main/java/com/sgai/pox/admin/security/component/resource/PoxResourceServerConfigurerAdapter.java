package com.sgai.pox.admin.security.component.resource;

import com.sgai.pox.admin.security.annotation.AnonymousAccess;
import com.sgai.pox.admin.security.annotation.Inner;
import com.sgai.pox.admin.security.component.PoxAuthenticationEntryPointImpl;
import com.sgai.pox.admin.security.component.PoxUserAuthenticationConverter;
import com.sgai.pox.admin.security.util.AuthorizeRequestUtil;
import com.sgai.pox.engine.common.core.exception.BaseException;
import com.sgai.pox.engine.common.core.util.CommonUtil;
import com.sgai.pox.engine.common.core.util.SpringContextUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author pox
 * @date
 */
@Slf4j
public class PoxResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    protected PoxAuthenticationEntryPointImpl authenticationEntryPointImpl;

    @Autowired
    private PoxResourceProperties poxResourceProperties;

    @Autowired(required = false)
    private RestTemplate lbRestTemplate;

    @Autowired(required = false)
    private RemoteTokenServices remoteTokenServices;

    @Override
    @SneakyThrows
    public void configure(HttpSecurity httpSecurity) {
        // 搜寻匿名标记 url： @AnonymousAccess
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap =
                SpringContextUtils.getApplicationContext().getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> anonymousUrls = new HashSet<>();
        Set<String> innerUrls = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (null != anonymousAccess) {
                anonymousUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
            if (poxResourceProperties.getInner()) {
                Inner inner = handlerMethod.getMethodAnnotation(Inner.class);
                if (null != inner) {
                    innerUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                }
            }
        }

        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                httpSecurity.authorizeRequests();
        poxResourceProperties.getIgnoreUrls().forEach(url -> registry.antMatchers(url).permitAll());
        anonymousUrls.forEach(url -> registry.antMatchers(url).permitAll());
        innerUrls.forEach(url -> registry.antMatchers(url).permitAll());
        List<AuthorizeRequest> authorizeRequests = poxResourceProperties.getAuthorizeRequests();
        AuthorizeRequestUtil.resolveAuthorizeRequest(registry, authorizeRequests);
        registry.and().csrf().disable();

        if (CommonUtil.isNotEmptyObject(poxResourceProperties.getAddFilterBeforeUsernamePasswordAuthenticationFilter())) {
            List<String> addFilterBeforeUsernamePasswordAuthenticationFilter =
                    poxResourceProperties.getAddFilterBeforeUsernamePasswordAuthenticationFilter();
            addFilterBeforeUsernamePasswordAuthenticationFilter.forEach(item -> {
                try {
                    Filter filter = (Filter) SpringContextUtils.getBean(Class.forName(item));
                    registry.and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                } catch (Exception e) {
                    throw new BaseException("设置过滤器失败");
                }
            });
        }
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authenticationEntryPointImpl);
        if (remoteTokenServices != null) {
            DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
            UserAuthenticationConverter userTokenConverter = new PoxUserAuthenticationConverter();
            accessTokenConverter.setUserTokenConverter(userTokenConverter);
            if (lbRestTemplate != null) {
                remoteTokenServices.setRestTemplate(lbRestTemplate);
            }
            remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
            resources.tokenServices(remoteTokenServices);
        }
    }

}
