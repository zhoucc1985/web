package com.cloud.annotation.execl;

import java.lang.annotation.*;

/**excel解析的注解
 * 1.@Target:描述注解的作用范围，取值(ElementType)有：
 * 　　　　1.CONSTRUCTOR:用于描述构造器
 * 　　　　2.FIELD:用于描述域
 * 　　　　3.LOCAL_VARIABLE:用于描述局部变量
 * 　　　　4.METHOD:用于描述方法
 * 　　　　5.PACKAGE:用于描述包
 * 　　　　6.PARAMETER:用于描述参数
 * 　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 *
 * 2.@Retention:表示需要在什么级别保存该注释信息取值（RetentionPolicy）有：
 * 　　　　1.SOURCE:在源文件中有效（即源文件保留）
 * 　　　　2.CLASS:在class文件中有效（即class保留）
 * 　　　　3.RUNTIME:在运行时有效（即运行时保留）(常用)
 *
 * 3.@Documented：Documented是一个标记注解
 * 4.@Inherited :用于声明一个注解；
 *
 * @author shrChang.Liu
 * @date 2017年12月7日下午4:28:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExcelResolve {
	/**
	 * 用于标注导入导出的表头的名称
	 * @auth shrChang.Liu
	 * @date 2017年12月7日下午4:44:07
	 * @return
	 */
	String titleName() default "";

	/**
	 * 是否导出到execl 默认是导出
	 * @auth shrChang.Liu
	 * @date 2017年12月7日下午4:45:20
	 * @return
	 */
	boolean isExport() default true;

	/**
	 * 是否导入到数据
	 * @return 默认是导入的
	 */
	boolean isImport() default true;

	/**
	 * 是否必须导入 因为有些只是用于导出的，如果是必填代表不能为空且必须有值
	 * @auth shrChang.Liu
	 * @date 2017年12月7日下午4:46:09
	 * @return
	 */
	boolean isRequired() default false;

	/**
	 * 当且仅当用于isRequired为true的时候的双重判断，如果存在下面名称的则为必填，否则选填
	 * @auth shrChang.Liu
	 * @date 2017年12月13日下午5:28:36
	 * @return
	 */
	String requiredName() default "";

	/**
	 * 正则表达式判断
	 * @auth shrChang.Liu
	 * @date 2017年12月13日下午3:50:09
	 * @return
	 */
	String regexText() default "";

	/**
	 * 这个是正则判断的描述
	 * @auth shrChang.Liu
	 * @date 2017年12月13日下午3:50:18
	 * @return
	 */
	String regexDesc() default "";
}
