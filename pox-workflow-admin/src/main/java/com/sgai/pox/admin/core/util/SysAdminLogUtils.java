package com.sgai.pox.admin.core.util;


import com.sgai.pox.engine.common.core.base.LogInfo;
import com.sgai.pox.engine.common.core.util.DateUtil;
import com.sgai.pox.engine.common.core.util.IpUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author pox
 */
public class SysAdminLogUtils {
    public static LogInfo getLogInfo() {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        LogInfo logInfo = new LogInfo();
        String userId = SecurityUtils.getUserId();
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
        Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }
}
