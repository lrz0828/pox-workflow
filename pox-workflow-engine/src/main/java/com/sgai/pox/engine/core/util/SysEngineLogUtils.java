package com.sgai.pox.engine.core.util;


import com.sgai.pox.engine.core.base.LogInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author pox
 */
public class SysEngineLogUtils {
    public static LogInfo getLogInfo() {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        LogInfo logInfo = new LogInfo();
        String userId = "default";
        logInfo.setUserId(userId);
        logInfo.setIp(IpUtils.getIpAddr(request));
        logInfo.setRequestUrl(request.getRequestURI());
        logInfo.setRequestType(request.getMethod());
        logInfo.setUserAgent(request.getHeader("user-agent"));
        logInfo.setClientId(getClientId());
        logInfo.setCreateBy(userId);
        logInfo.setCreateDate(DateUtil.getNow());
        logInfo.setCreateTime(DateUtil.getNow());
        return logInfo;
    }

    public static String getClientId() {
//        Authentication authentication = SecurityEngineUtils.getAuthentication();
//        if (authentication instanceof OAuth2Authentication) {
//            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
//            return auth2Authentication.getOAuth2Request().getClientId();
//        }
        return null;
    }
}
