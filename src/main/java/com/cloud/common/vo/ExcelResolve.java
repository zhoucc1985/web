package com.cloud.common.vo;

import java.lang.annotation.*;

/**excel解析的注解
 * @ClassName EexelResolve.java
 * @author Pan Jianneng
 * @date 2018年10月21日下午4:28:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExcelResolve {
	/**
	 * 用于标注导入导出的表头的名称
	 * @auth Pan Jianneng
	 * @date 2018年10月21日下午4:44:07
	 * @return
	 */
	String titleName() default "";

	/**
	 * 是否导出到execl 默认是导出
	 * @auth Pan Jianneng
	 * @date 2018年10月21日下午4:45:20
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
	 * @auth Pan Jianneng
	 * @date 2018年10月21日下午4:46:09
	 * @return
	 */
	boolean isRequired() default false;

	/**
	 * 当且仅当用于isRequired为true的时候的双重判断，如果存在下面名称的则为必填，否则选填
	 * @auth Pan Jianneng
	 * @date 2018年10月21下午5:28:36
	 * @return
	 */
	String requiredName() default "";

	/**
	 * 正则表达式判断
	 * @auth Pan Jianneng
	 * @date 2018年10月21下午3:50:09
	 * @return
	 */
	String regexText() default "";

	/**
	 * 这个是正则判断的描述
	 * @auth Pan Jianneng
	 * @date 2018年10月21下午3:50:18
	 * @return
	 */
	String regexDesc() default "";
}
