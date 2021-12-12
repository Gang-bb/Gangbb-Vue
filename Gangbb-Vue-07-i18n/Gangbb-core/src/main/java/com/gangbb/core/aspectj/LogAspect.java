package com.gangbb.core.aspectj;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangbb.common.annotation.Log;
import com.gangbb.common.enums.BusinessStatus;
import com.gangbb.common.utils.ServletUtils;
import com.gangbb.common.utils.StringUtils;
import com.gangbb.common.utils.ip.AddressUtils;
import com.gangbb.common.utils.ip.IpUtils;
import com.gangbb.core.manager.AsyncManager;
import com.gangbb.core.manager.factory.AsyncFactory;
import com.gangbb.core.model.entity.sys.SysOperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : LogAspect
 * @Description : 操作日志记录处理
 * @Date : 2021/3/7 13:20
 */
@Aspect
@Component
public class LogAspect {
    /**
     * 定义一个log用于打印
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.gangbb.common.annotation.Log)")
    public void logPointCut(){
    }

    /**
     * 处理完请求前执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) throws JsonProcessingException {
        handleLog(joinPoint, null, jsonResult);
        //处理完请求，返回内容. new ObjectMapper()用于对象转json
        log.info("RESPONSE : " + new ObjectMapper().writeValueAsString(jsonResult));
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }


    /**
     * Log具体处理逻辑
     * @param joinPoint
     * @param e
     * @param jsonResult
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult){
        try {
            // 1. 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            // 2. 从缓存获取当前的用户信息(暂缺模拟一个)
            String username = "Gangbb";

            // 3. 获取请求各项需要记录的基本信息
            SysOperationLog sysOperationLog = new SysOperationLog();
            // 记录请求的时间
            sysOperationLog.setOperationTime(new Date());
            // 记录成功操作状态
            sysOperationLog.setOperationStatus(BusinessStatus.SUCCESS.ordinal());
            // 请求的Ip
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            sysOperationLog.setOperationIp(ip);
            // 返回的参数 用fastjson转类型
            sysOperationLog.setJsonResult(JSON.toJSONString(jsonResult));
            // 访问的url
            sysOperationLog.setOperationUrl(ServletUtils.getRequest().getRequestURI());
            // 访问的用户名
            sysOperationLog.setOperationName(username);
            // 设置方法名称 类名+方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            sysOperationLog.setClassMethod(className + "." + methodName + "()");
            // 设置请求方式
            sysOperationLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 记录请求的地理位置
            sysOperationLog.setOperationLocation(AddressUtils.getRealAddressByIP(sysOperationLog.getOperationIp()));
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, sysOperationLog);

            // 4.判断请求是否有异常
            if (e != null) {
                // 记录失败操作状态
                sysOperationLog.setOperationStatus(BusinessStatus.FAIL.ordinal());
                // 记录失败的错误信息
                sysOperationLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }

            // 5.保存到数据库
            AsyncManager.me().execute(AsyncFactory.recordOperation(sysOperationLog));

            // 6.打印到控制台
            logToConsole(sysOperationLog);

        }catch (Exception exp){
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }

    }

    /**
     *  请求打印到控制台
     * @param sysOperationLog
     */
    public void logToConsole(SysOperationLog sysOperationLog){
        log.info("URL : " + sysOperationLog.getOperationUrl().toString());
        log.info("HTTP_METHOD :" + sysOperationLog.getRequestMethod());
        log.info("IP : " + sysOperationLog.getOperationIp());
        log.info("CLASS_METHOD : " + sysOperationLog.getClassMethod());
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operationLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperationLog operationLog) throws Exception
    {
        // 设置action动作
        operationLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operationLog.setTitle(log.title());
        // 设置操作人类别
        operationLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operationLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operationLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperationLog operationLog) throws Exception
    {
        String requestMethod = operationLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))
        {
            String params = argsArrayToString(joinPoint.getArgs());
            operationLog.setOperationParam(StringUtils.substring(params, 0, 2000));
        }
        else
        {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operationLog.setOperationParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }
    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (int i = 0; i < paramsArray.length; i++)
            {
                if (!isFilterObject(paramsArray[i]))
                {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }
    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Iterator iter = collection.iterator(); iter.hasNext();)
            {
                return iter.next() instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext();)
            {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }


}
