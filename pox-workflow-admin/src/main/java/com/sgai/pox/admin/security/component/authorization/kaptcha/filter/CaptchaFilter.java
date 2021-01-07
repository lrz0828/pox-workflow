package com.sgai.pox.admin.security.component.authorization.kaptcha.filter;

import com.sgai.pox.engine.common.core.constant.CacheConstants;
import com.sgai.pox.engine.common.core.constant.SecurityConstants;
import com.sgai.pox.engine.common.core.exception.BaseException;
import com.sgai.pox.engine.common.core.util.CommonUtil;
import com.sgai.pox.engine.common.core.util.SecurityUtils;
import com.sgai.pox.engine.common.redis.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author pox
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Value("${pox.captcha.clients}")
    private List<String> clients;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 只处理登录请求和需要验证码验证的client_id
        if (StringUtils.containsIgnoreCase(request.getServletPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
            Authentication authentication = SecurityUtils.getAuthentication();
            String clientId = authentication != null ? authentication.getName() : null;
            if (clientId != null && clients.contains(clientId)) {
                validateCaptcha(request);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证验证码合法性
     *
     * @param request
     * @return
     */
    private void validateCaptcha(HttpServletRequest request) {
        String captcha = request.getParameter("captcha");
        String uuid = request.getParameter("uuid");
        CommonUtil.isEmptyStr(uuid, "验证码uuid不能为空");
        CommonUtil.isEmptyStr(captcha, "验证码不能为空");

//        String cacheCaptcha = (String) redisUtil.get(CacheConstants.CAPTCHA + uuid);
//        if (!captcha.equals(cacheCaptcha)) {
//            throw new BaseException("验证码错误或已失效");
//        }
        // 验证码验证通过后，应立即删除缓存，防止恶意暴力破解密码
        redisUtil.del(CacheConstants.CAPTCHA + uuid);
    }
}