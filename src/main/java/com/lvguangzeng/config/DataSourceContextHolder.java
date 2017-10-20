package com.lvguangzeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {
	public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

	/**
	 * 默认数据源
	 */
	public static final String DEFAULT_DATASOURCE = "db1DataSource";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	// 设置数据源名
	public static void setDataSource(String dbType) {
		log.info("切换到{}数据源", dbType);
		contextHolder.set(dbType);
	}

	// 获取数据源名
	public static String getDataSource() {
		return (contextHolder.get());
	}

	// 清除数据源名
	public static void clearDataSource() {
		contextHolder.remove();
	}

}
