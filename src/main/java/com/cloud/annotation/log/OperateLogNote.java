package com.cloud.annotation.log;

import java.lang.annotation.*;


/**
 * 操作日志注解
 * @ClassName OperateLogNote.java
 * @author shrChang.Liu
 * @date 2017年12月20日下午3:52:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OperateLogNote {

	/**
	 * 模块名称
	 * @auth shrChang.Liu
	 * @date 2017年12月20日下午3:54:54
	 * @return
	 */
	String module() default "";
	
	/**
	 * 操作名称 例如：
	 * @auth shrChang.Liu
	 * @date 2017年12月20日下午3:55:17
	 * @return
	 */
	String name() default "";
	
	/**
	 * 操作类型 Add/Update/Del/Query 默认是查询
	 * @auth shrChang.Liu
	 * @date 2017年12月20日下午3:55:44
	 * @return
	 */
	OperateLogType type() default OperateLogType.QUERY;
}
