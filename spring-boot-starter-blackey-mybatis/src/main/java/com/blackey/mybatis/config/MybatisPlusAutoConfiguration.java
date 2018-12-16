package com.blackey.mybatis.config;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * MybatisPlus 配置
 * Created by Kaven
 * Date: 2018/6/8
 */
@Configuration
public class MybatisPlusAutoConfiguration {

    private static final String SYSTEM_TENANT_ID = "tenant_id";
    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("sys_tenant_info");


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {


        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        // SQL解析处理拦截：增加租户处理回调。
        TenantSqlParser tenantSqlParser = new TenantSqlParser()
                .setTenantHandler(new TenantHandler() {

                    @Override
                    public Expression getTenantId() {
                        // 从当前系统上下文中取出当前请求的服务商ID，通过解析器注入到SQL中。
                        Long currentTenantId = 1L;
                        if (null == currentTenantId) {
                            throw new RuntimeException("#1129 getCurrentProviderId error.");
                        }
                        return new LongValue(currentTenantId);
                    }

                    @Override
                    public String getTenantIdColumn() {
                        return SYSTEM_TENANT_ID;
                    }

                    @Override
                    public boolean doTableFilter(String tableName) {
                        // 忽略掉一些表：如租户表（provider）本身不需要执行这样的处理。
                        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
                    }
                });
        paginationInterceptor.setSqlParserList(Lists.newArrayList(tenantSqlParser));



        return new PaginationInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /**
         * SQL执行性能分析，开发环境使用
         */
        performanceInterceptor.setMaxTime(10000);
        /**
         * SQL 是否格式化 默认false
         */
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * 逻辑删除injector
     * @return
     */
    @Bean
    public LogicSqlInjector logicSqlInjector(){
        return new LogicSqlInjector();
    }
}
