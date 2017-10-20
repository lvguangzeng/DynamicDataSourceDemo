package com.lvguangzeng.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Bean(name = "db1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	public DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "db2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "DynamicDataSource")
    @Primary
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(db1DataSource());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("db1DataSource",  db1DataSource());
        dsMap.put("db2DataSource", db2DataSource());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
