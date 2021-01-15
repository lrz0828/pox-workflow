package com.sgai.pox.engine.core.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.sgai.pox.engine.core.mybatis.permission.PermissionParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Configuration
@MapperScan("com.**.mapper*")
public class MybatisPlusConfig {

    @Bean
    @ConditionalOnProperty(name = "pox.data-permission.enabled", havingValue = "true")
    public PermissionParser permissionParser() {
        return new PermissionParser();
    }

    @Bean
    @ConditionalOnMissingBean(PermissionParser.class)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    @Bean
    @ConditionalOnBean(PermissionParser.class)
    public PaginationInterceptor paginationInterceptor2(PermissionParser permissionParser) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setSqlParserList(Collections.singletonList(permissionParser));
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
