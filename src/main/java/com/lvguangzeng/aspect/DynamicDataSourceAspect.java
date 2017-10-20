package com.lvguangzeng.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.lvguangzeng.annotation.DB;
import com.lvguangzeng.config.DataSourceContextHolder;

@Aspect
@Component
public class DynamicDataSourceAspect {

	@Before("@annotation(com.lvguangzeng.annotation.DB)")
	public void beforeSwitchDataSource(JoinPoint joinPoint) {
		// 获得当前访问的class
		Class<?> className = joinPoint.getTarget().getClass();

		// 获得访问的方法名
		String methodName = joinPoint.getSignature().getName();
		// 得到方法的参数的类型
		@SuppressWarnings("rawtypes")
		Class[] argClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
		String dataSource = DataSourceContextHolder.DEFAULT_DATASOURCE;
		try {
			// 得到访问的方法对象
			Method method = className.getMethod(methodName, argClass);

			// 判断是否存在@DS注解
			if (method.isAnnotationPresent(DB.class)) {
				DB annotation = method.getAnnotation(DB.class);
				// 取出注解中的数据源名
				dataSource = annotation.value();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 切换数据源
		DataSourceContextHolder.setDataSource(dataSource);
	}

	@After("@annotation(com.lvguangzeng.annotation.DB)")
	public void afterSwitchDS(JoinPoint point) {

		DataSourceContextHolder.clearDataSource();

	}

}
