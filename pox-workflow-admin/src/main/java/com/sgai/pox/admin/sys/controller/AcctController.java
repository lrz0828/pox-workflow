package com.sgai.pox.admin.sys.controller;

import com.sgai.pox.admin.sys.service.SysUserService;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.constant.CacheConstants;
import com.sgai.pox.engine.core.exception.BaseException;
import com.sgai.pox.engine.core.redis.util.RedisUtil;
import com.sgai.pox.engine.core.session.AcctLogin;
import com.sgai.pox.engine.core.session.CacheKeyConstant;
import com.sgai.pox.engine.core.session.SecurityUser;
import com.sgai.pox.engine.core.util.CommonUtil;
import com.sgai.pox.engine.core.util.CookieUtil;
import com.sgai.pox.engine.core.util.WebUtil;
import com.sgai.pox.engine.core.util.base64.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/oauth")
public class AcctController {

    private static final Logger LOG = LoggerFactory.getLogger(AcctController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/token")
    public SecurityUser userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                                  HttpServletRequest request , HttpServletResponse response){
        AcctLogin acctLogin = new AcctLogin();
        LOG.info("username = {}", username);
        String id = request.getSession().getId();
        LOG.info("sessionId = {}", id);
        acctLogin.setUserName(username);
        acctLogin.setPassword(password);
        acctLogin.setSessionId(id);
        acctLogin.setClientType(WebUtil.getClientType(request));

        validateCaptcha(request);
        SecurityUser securityUser = sysUserService.userLogin(acctLogin);
        CookieUtil.setCookie(response,"pox_sessionId", securityUser.getAccess_token());
        return securityUser;
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request , HttpServletResponse response){
        String id = request.getSession().getId();
        LOG.info("sessionId = {}", id);

        String key = CacheKeyConstant.ACCT_SESSION + Base64Util.decode(id);
        redisUtil.del(key);
        CookieUtil.removeCookie(response, "pox_sessionId");

        return Result.ok();
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

        // TODO: 2021/1/14
//        String cacheCaptcha = (String) redisUtil.get(CacheConstants.CAPTCHA + uuid);
//        if (!captcha.equals(cacheCaptcha)) {
//            throw new BaseException("验证码错误或已失效");
//        }
//        // 验证码验证通过后，应立即删除缓存，防止恶意暴力破解密码
//        redisUtil.del(CacheConstants.CAPTCHA + uuid);
    }
}
