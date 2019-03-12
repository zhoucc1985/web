package com.cloud.common.excption;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.cloud.common.utils.CommonUtils;
/**
 * 接口异常处理
 * Api异常转换成正确返回结果给前端
 * 当调用api接口发生异常时，用户可以收到正常的数据格式
 * @author Pan jianneng
 * @time 2018/7/25 18:05
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlerAdvice.class);

    /**
     * 异常处理，其他未知Exception异常
     * @author Pan Jianneng
     * @date 2018/11/15 11:07 AM
     * @param ex
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ExceptionHandler(Exception.class)
    public Map<String,Object> exceptionHandler(HttpServletRequest request,Exception ex){
        return handle(ex,request);
    }

    /**
     * 部分异常处理
     * @author Pan Jianneng
     * @date 2018/11/16 4:02 PM
     * @params [e]
     * @return java.lang.String
     */
    public Map<String,Object>  handle(Exception e,HttpServletRequest request) {
        //错误返回map
        Map<String,Object> resultMap = CommonUtils.getErrorResultMap();
        String message = null;
        Throwable throwableLast = handleCause(e);
        //异常名字
        String exceptionName = throwableLast.getClass().getName();
        //在这里判断异常，根据不同的异常返回错误。
        if (exceptionName.equals(DataAccessException.class.getName())) {
            message = "数据库操作失败";
            logger.error("数据库操作失败！"+e.getMessage());
        } else if (exceptionName.equals(NullPointerException.class.getName())) {
            message = "空指针异常！";
            logger.error("空指针异常！"+e.getMessage());
        } else if (exceptionName.equals(IOException.class.getName())) {
            message = "IO异常！";
            logger.error("IO异常！"+e.getMessage());
        } else if (exceptionName.equals(ClassNotFoundException.class.getName())) {
            message = "指定的类不存在！";
            logger.error("指定的类不存在！"+e.getMessage());
        } else if (exceptionName.equals(ArithmeticException.class.getName())) {
            message = "数学运算异常！";
            logger.error("数学运算异常！"+e.getMessage());
        } else if (exceptionName.equals(ArrayIndexOutOfBoundsException.class.getName())) {
            message = "数组下标越界！";
            logger.error("数组下标越界！"+e.getMessage());
        } else if (exceptionName.equals(IllegalArgumentException.class.getName())) {
            message = e.getLocalizedMessage();
            logger.error(e.getLocalizedMessage());
        } else if (exceptionName.equals(ClassCastException.class.getName())) {
            message = "类型强制转换错误！";
            logger.error("类型强制转换错误！"+e.getMessage());
        }else if (exceptionName.equals(SQLException.class.getName())) {
            message = "操作数据库异常！";
            logger.error("操作数据库异常！"+e.getMessage());
        } else if (exceptionName.equals(NoSuchMethodError.class.getName())) {
            message = "方法未找到！";
            logger.error("方法未找到"+e.getMessage());
        } else if (exceptionName.equals(InternalError.class.getName())) {
            message = "Java虚拟机发生了内部错误！";
            logger.error("Java虚拟机发生了内部错误"+e.getMessage());
        }else if(exceptionName.equals(MethodArgumentTypeMismatchException.class.getName())){
            message = "参数类型错误！";
            logger.error("Java虚拟机发生了内部错误"+e.getMessage());
        }else if(exceptionName.equals(NoHandlerFoundException.class.getName())){
            message = "请求的目录或资源不存在，请检查URL地址！";
            logger.error("请求的目录或资源不存在，请检查URL地址！"+e.getMessage());
        }else if(exceptionName.equals(NumberFormatException.class.getName())){
            message = "数字格式错误！";
            logger.error("数字格式错误！",e.getMessage());
        }else if(exceptionName.equals(BusinessException.class.getName())){
            message = e.getMessage();
            BusinessException bus = (BusinessException) e;
            resultMap.put("datas",bus.getResourceId());
            logger.error("业务异常："+bus.getMessage());
        }else if(exceptionName.equals(HttpRequestMethodNotSupportedException.class.getName())){
            message = "服务器网络异常，请重新登录";
            logger.error("请求异常："+e.getMessage());
        }else if(exceptionName.equals(org.springframework.web.bind.MethodArgumentNotValidException.class.getName())){
    	    message = "数据参数异常";
    	    logger.error("数据参数异常："+e.getMessage());
        }else {
            message = "服务异常";
            logger.error("服务异常:"+e);
        }
        resultMap.put("msg",message);
        //请求路径
        resultMap.put("url",request.getRequestURI());
        return resultMap;
    }

    /**
     * 获取抛出异常
     * @author Pan Jianneng
     * @date 2018/11/16 4:12 PM
     * @params [e]
     * @return java.lang.Throwable
     */
    public Throwable handleCause(Throwable e) {
        if (e.getCause() != null) {
            return handleCause(e.getCause());
        }else {
            return e;
        }
    }


}
