package com.sgai.pox.admin.core.util;


import com.sgai.pox.engine.core.constant.EnvConstants;
import com.sgai.pox.engine.core.util.StringHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Configuration
public class EnvironmentUtil implements EnvironmentAware {

    private static Logger logger = LoggerFactory.getLogger(EnvironmentUtil.class);

    private static Environment env;

    /**
     * 通过key 获取配置值
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        if (null == env || StringUtils.isBlank(key)) {
            return null;
        }
        String val = env.getProperty(key);
        return val;
    }

    @SuppressWarnings("static-access")
    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    //spring.config.location
    /**
     * 从main函数的参数里面截取spring.config.location的值放入系统变量(Hbase获取链接时要用)
     * @param args
     */
	public static void setSystemEnv(String[] args) {
		if (args != null && args.length > 0) {
			String profile = StringHelper.getConfigLocationByArgs(args);
			System.setProperty(EnvConstants.ENV_PROFILE_KEY, profile);
			System.setProperty("ip", getInetAddr());
		}
	}

    /**
     * 获取本机IP地址.
     *
     * @return 本机IP地址
     */
    public static String getInetAddr() {
        String inetAddr = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            inetAddr = addr.getHostAddress();
        } catch (Exception ex) {
            logger.debug("获取IP地址出错!");
        }
        return StringUtils.isBlank(inetAddr) ? "127.0.0.1" : inetAddr;
    }
}
