package com.lvguangzeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/**
 * 
 * @author lvguangzeng
 *
 */
/*
 * 禁用数据源自动配置 exclude= {DataSourceAutoConfiguration.class}
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DynamicDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDataSourceApplication.class, args);
	}
}
