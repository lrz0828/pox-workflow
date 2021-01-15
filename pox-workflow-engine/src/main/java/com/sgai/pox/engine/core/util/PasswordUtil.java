package com.sgai.pox.engine.core.util;

import org.flowable.idm.api.PasswordEncoder;

/***
 * 密码加密解密
 *
 * @author pox
 */
public class PasswordUtil {


    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        return MD5Util.encrypByMd5(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return encodedPassword.equals(encryptPassword(rawPassword));
    }
}