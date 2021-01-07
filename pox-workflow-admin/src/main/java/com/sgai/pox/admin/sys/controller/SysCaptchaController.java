package com.sgai.pox.admin.sys.controller;

import com.google.code.kaptcha.Producer;
import com.sgai.pox.admin.security.annotation.AnonymousAccess;
import com.sgai.pox.engine.common.core.constant.CacheConstants;
import com.sgai.pox.engine.common.core.util.SpringContextUtils;
import com.sgai.pox.engine.common.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author pox
 * @date 2021年01月04日
 */
@RestController
public class SysCaptchaController {
    @Autowired
    private RedisUtil redisUtil;

    @AnonymousAccess
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        Producer producer = SpringContextUtils.getBean(Producer.class);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 保存到 redis,60秒
        redisUtil.set(CacheConstants.CAPTCHA + uuid, text, 60);
        // 获取图片验证码
        BufferedImage image = producer.createImage(text);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
