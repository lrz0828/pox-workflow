package com.sgai.pox.engine.filter;

import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.constant.CacheConstants;
import com.sgai.pox.engine.core.redis.util.RedisUtil;
import com.sgai.pox.engine.core.session.AcctSession;
import com.sgai.pox.engine.core.session.AssertContext;
import com.sgai.pox.engine.core.session.CacheKeyConstant;
import com.sgai.pox.engine.core.util.CookieUtil;
import com.sgai.pox.engine.core.util.JacksonUtil;
import com.sgai.pox.engine.core.util.StringUtil;
import com.sgai.pox.engine.core.util.WebUtil;
import com.sgai.pox.engine.core.util.base64.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: pox
 */
@Component
@WebFilter(value = {"/*"},filterName = "loginStatusFilter")
public class LoginStatusFilter implements Filter {

    private final static Logger LOG =LoggerFactory.getLogger(LoginStatusFilter.class);


    //不拦截的url
    private List<String> ALLOWED_PATHS = new ArrayList<>();

    //不拦截文件
    private List<String> ALLOWED_SUFFIX = new ArrayList<>();

    @Autowired
    private RedisUtil redisUtil;

    @Value("${pox.resource.config.ignoreUrls}" )
    private String allowedUrl;

    @Value("${pox.resource.config.suffix}")
    private String allowedSuffix;

    @Value("${pox.resource.homeUrl}")
    private String returnUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("初始化信息 --");
        ALLOWED_PATHS = Arrays.asList(allowedUrl.split(","));
        ALLOWED_SUFFIX = Arrays.asList(allowedSuffix.split(","));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;

        //不拦截
        String requestURL = servletRequest.getRequestURI().substring(servletRequest.getContextPath().length()).replaceAll("[/]+$", "");
        LOG.info("requestURL = {}", requestURL);

        String startType = System.getProperty("start.TYPE");
        // 1 admin 2 starter 其他情况默认为 engine被单独引用
        if (StringUtil.isNotEmpty(startType) && "1".equals(startType)) {
            if(StringUtil.isEmpty(requestURL) || allowedUrl(requestURL) || allowedSuffix(requestURL)){
                chain.doFilter(request, response);
                return ;
            } else {

            }

            //验证用户是否登录..
            String sessionId = CookieUtil.getSessionId(servletRequest);
            LOG.info("sessionId = {}", sessionId);
            AcctSession acctSession = this.getUser(sessionId);

            if (Objects.isNull(acctSession)) {
                CookieUtil.removeCookie(servletResponse,"pox_sessionId");
                //获得缓存失败或登录状态无效 调到登录页面
                LOG.info("登录状态无效!");
                if (WebUtil.isJsonRequest(servletRequest)) {
                    servletRequest.setCharacterEncoding("UTF-8");
                    Result result = new Result(1, "请重新登录！");
                    servletResponse.setContentType("application/json; charset=UTF-8"); // 转码
                    servletResponse.getWriter().write(JacksonUtil.objToStr(result));
                    return;
                }  else {
                    servletRequest.setCharacterEncoding("UTF-8");
                    servletResponse.setContentType("text/html; charset=UTF-8"); // 转码
                    servletResponse.getWriter()
                            .println("<script language=\"javascript\">if(window.opener==null){window.top.location.href=\""
                                    + returnUrl + "\";}else{window.opener.top.location.href=\"" + returnUrl
                                    + "\";window.close();}</script>");
                    return;
                }
            }

            LOG.info("用户登录成功!");
            AssertContext.init(acctSession);
            CookieUtil.setCookie(servletResponse,"pox_sessionId", sessionId);
            chain.doFilter(request, response);
        } else {
            // TODO: 2021/1/13 校验是否传用户信息，或者通过token解析
        }

        
    }


    @Override
    public void destroy() {
        AssertContext.destroy();
    }


    /**
     * 不拦截请求
     * @param requestURL
     * @return
     */
    private boolean allowedSuffix(String requestURL) {

        if(requestURL.contains(".")){
            String suffix = requestURL.substring(requestURL.lastIndexOf("."));

            return ALLOWED_SUFFIX.stream().anyMatch(suf -> suf.equals(suffix));
        }

        return false;
    }

    /**
     * 不拦截 url
     * @param requestURL
     * @return
     */
    private boolean allowedUrl(String requestURL) {

        boolean allowed = ALLOWED_PATHS.contains(requestURL);

        if(!allowed){
            allowed = ALLOWED_PATHS.stream().filter(x -> x.contains("*"))
                    .map(x -> x.substring(0,x.lastIndexOf("*"))).anyMatch(x -> requestURL.contains(x));
        }

        return allowed;
    }

    private AcctSession getUser(String sessionId) {
        AcctSession acctSession = null;
        if (StringUtil.isEmpty(sessionId)) {
            return null;
        }
        String key = CacheKeyConstant.ACCT_SESSION + Base64Util.decode(sessionId);
        redisUtil.expire(key, CacheConstants.TIME_EXPIRE);
        String acctSessionJson = (String) redisUtil.get(key);
        if (StringUtil.isNotEmpty(acctSessionJson)) {
            acctSession = JacksonUtil.strToObj(acctSessionJson, AcctSession.class);
        }

        return acctSession;
    }
}
