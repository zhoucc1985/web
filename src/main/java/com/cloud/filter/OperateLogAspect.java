package com.cloud.filter;

import com.cloud.annotation.log.OperateLogNote;
import com.cloud.common.dict.cloud.CommonDict;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.cloud.UserUtils;
import com.cloud.entity.cloud.OperateLog;
import com.cloud.mapper.master.cloud.OperateLogMapper;
import com.cloud.shiro.cloud.ShiroUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 系统操作日志
 * @ClassName OperateLogAspect.java
 * @author shrChang.Liu
 * @date 2017年12月20日下午4:32:26
 */
@Aspect
@Component
public class OperateLogAspect {
	
	private static Logger logger = LoggerFactory.getLogger(OperateLogAspect.class);
	
	@Resource
	private OperateLogMapper logMapper;

	/**
	 * 开始时间
	 */
	private Date startDate = new Date();
	
	@Pointcut("@annotation(com.cloud.annotation.log.OperateLogNote)")
	public void operateAspect(){
	}
	
	@Before("operateAspect()")
	public void doBeforeMethod(){
		startDate = new Date();
	}
	
	@SuppressWarnings("unchecked")
	@AfterReturning(pointcut="operateAspect()",returning="vtr")
	public void doAfterMethod(JoinPoint joinPoint,Object vtr){
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String ip = request.getRemoteAddr();
			ShiroUser user = UserUtils.getShiroUser();
			Method method = getMethodAnnotation(joinPoint);
			OperateLogNote logNote = method.getAnnotation(OperateLogNote.class);
			if(logNote != null){
				boolean isSucess = true;
				if(vtr instanceof Map){
					Map<String,Object> result = (Map<String,Object>)vtr;
					if(result.containsKey(CommonDict.STATE)){
						String state = (String)result.get(CommonDict.STATE);
						if(state.equals(CommonDict.RETURN_STATE_FAIL)){
							isSucess = false;
						}
					}
				}
				OperateLog log = createOpreateLog(user, logNote, joinPoint);
				RequestMapping mapping = method.getAnnotation(RequestMapping.class);
				if(mapping != null){
					RequestMethod[] requestMethod = mapping.method();
					if(requestMethod.length==0){
						log.setOperateMethodType(RequestMethod.GET.name());
					}else{
						log.setOperateMethodType(requestMethod[0].name());
					}

				}
				log.setIsSucess(isSucess);
				log.setOperateIpv4(ip);
				logMapper.insertSelective(log);
			}
		} catch (Exception e) {
			logger.error("处理方法返回错误：",e);
		}
		logger.info("*********执行操作完成************");
	}
	
	
	/**
	 * 创建操作日志
	 * @auth shrChang.Liu
	 * @date 2018年1月12日上午11:34:46
	 */
	private OperateLog createOpreateLog(ShiroUser user, OperateLogNote logNote, JoinPoint joinPoint){
		OperateLog log = new OperateLog();
		log.setCollegeId(user.collegeId);
		log.setCollegeName(user.collegeName);
		log.setCreateDate(startDate);
		log.setEndLogDate(new Date());
		log.setOperateDuration(System.currentTimeMillis()- startDate.getTime());
		log.setOperateUserId(user.id);
		log.setOperateUserName(user.name);
		log.setOperateMethodCode(joinPoint.getSignature().getName());
		Object[] objs = joinPoint.getArgs();
		String requestParam = "";
		for(Object obj : objs){
			if(obj instanceof HttpServletRequestWrapper){
				HttpServletRequestWrapper wrapper = (HttpServletRequestWrapper)obj;
				//先获取url后面的参数
				Map<String,String[]> map = wrapper.getParameterMap();
				//获取post里面的参数
				try {
					requestParam = requestParam + CommonUtils.getJsonStrByObject(map);
				} catch (Exception e) {
					logger.error("转化参数失败！",e);
				}
			}else{
				if(obj != null){
					try {
						requestParam = requestParam + CommonUtils.getJsonStrByObject(obj);
					} catch (Exception e) {
						logger.error("转化参数失败！",e);
					}
				}
			}
		}
		requestParam = requestParam.length() > 255 ? requestParam.substring(0,254) : requestParam;
		log.setOperateRequestParam(requestParam);
		
		//注解上面的字段
		log.setOperateModule(logNote.module());
		log.setOperateName(logNote.name());
		log.setOperateType(logNote.type().getName());
		return log;
	}
	
	/**
	 * 返回这个方法
	 * @auth shrChang.Liu
	 * @date 2017年12月20日下午4:53:50
	 * @return
	 */
	public Method getMethodAnnotation(JoinPoint joinPoint)throws Exception {
		Method m = null;
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					m = method;
					break;
				}
			}
		}
		return m;  
	}
	
}
