package com.sgai.pox.engine.core.util;

import org.springframework.context.ApplicationContext;

public class SpringBeanUtil {

	private static ApplicationContext applicationContext;

	//获取上下文
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	 
	//设置上下文
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringBeanUtil.applicationContext = applicationContext;
	}

	/**
	 * getBean
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	/**
	 * getBean
	 * 
	 * @param beanId
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String beanId, Class<T> clazz) {
		return applicationContext.getBean(beanId, clazz);
	}
	
	/**
	 * getBean
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}
	
	/**
	 * containsBean
	 * 
	 * @param beanId
	 * @return
	 */
	public static boolean containsBean(String beanId) {
		return applicationContext.containsBean(beanId);
	}

}
