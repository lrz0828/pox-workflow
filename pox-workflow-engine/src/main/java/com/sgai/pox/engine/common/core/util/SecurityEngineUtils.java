package com.sgai.pox.engine.common.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author pox
 */
public class SecurityEngineUtils {
    /**
     * 获取Authentication
     */
//    public static Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
    // TODO: 2021/1/7 暂时注掉
//    public static UserDetails getUserDetails() {
//        Authentication authentication = getAuthentication();
//        if (authentication == null || authentication.getPrincipal() == null) {
//            return null;
//        }
//        return (UserDetails) authentication.getPrincipal();
//    }

    public static String getUserId() {
//        Authentication authentication = getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//        return authentication.getName();

        return "default";
    }

    /// public static String resolveToken(HttpServletRequest request) {
    /// String bearerToken = request.getHeader("Authorization");
    /// if (bearerToken != null && !bearerToken.isEmpty() && bearerToken.startsWith("Bearer")) {
    /// return bearerToken.substring(7);
    /// }
    /// String token = request.getParameter("access_token");
    /// if (token != null && !token.isEmpty()) {
    /// return token;
    /// }
    /// return null;
    /// }
}
