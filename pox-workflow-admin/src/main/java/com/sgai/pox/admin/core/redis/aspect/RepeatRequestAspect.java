package com.sgai.pox.admin.core.redis.aspect;

import com.sgai.pox.admin.core.util.SecurityUtils;
import com.sgai.pox.engine.common.redis.aspect.components.RepeatRequestComponent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;

/**
 * 登录用户请求防重发处理,Order=0优先于RedissonLockAspect
 *
 * @author pox
 * @date 2021年01月04日
 */
@Aspect
@ConditionalOnBean(RepeatRequestComponent.class)
@Order(0)
public class RepeatRequestAspect {
    @Autowired
    private RepeatRequestComponent repeatRequestComponent;

    @Pointcut("execution(* com.*..*.controller.*.*(..))")
    private void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object controllerAspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return repeatRequestComponent.exec(joinPoint, SecurityUtils.getUserId());
    }
}
