package com.gangbb.common.exception;

import com.gangbb.common.model.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author : Gangbb
 * @ClassName : GlobalExceptionHandler
 * @Description : 局异常处理器
 * @Date : 2021/3/9 19:58
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * ApiException 自定义异常捕捉
     */
    @ExceptionHandler(ApiException.class)
    public ApiRestResponse baseException(ApiException e)
    {
        log.error("ApiException: ", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());
    }

    /**
     * BindException 参数校验异常捕捉
     */
    @ExceptionHandler(BindException.class)
    public ApiRestResponse exception(BindException e)
    {
        return handleBindingResult(e.getBindingResult());
    }

    /**
     * HTTPException 网络异常捕捉
     */
    @ExceptionHandler(HTTPException.class)
    public ApiRestResponse exception(HTTPException e)
    {
        log.error("HTTPException: ", e);
        //A0003--HTTP请求错误
        return ApiRestResponse.error("A0003", e.getMessage());
    }

    /**
     * ServletException 网络请求异常捕捉
     */
    @ExceptionHandler(ServletException.class)
    public ApiRestResponse exception(ServletException e)
    {
        log.error("ServletException: ", e);
        //A0003--HTTP请求错误
        return ApiRestResponse.error("A0003",e.getMessage());
    }
    /**
     * MethodArgumentTypeMismatchException 参数类型转换异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiRestResponse resolveMethodArgumentNotValidException(MethodArgumentTypeMismatchException ex){
        String msg = "参数<" + ex.getName() + ">校验失败：" +  ex.getMessage();
        return ApiRestResponse.error("A0003", msg);
    }

    /**
     * Exception 基类异常捕捉
     */
    @ExceptionHandler(Exception.class)
    public ApiRestResponse exception(Exception e)
    {
        log.error("Exception: ", e);
        // B0001--系统内部错误
        return ApiRestResponse.error("B0001");
    }



    /**
     * 处理参数校验异常信息
     * @param result
     * @return
     */
    private ApiRestResponse handleBindingResult(BindingResult result) {
        List<String> errorList = new ArrayList<>();
        if (result.hasErrors()) {
            List<FieldError> list = result.getFieldErrors();
            for (FieldError error : list) {
                String message = "<"+ error.getField() + ">校验不通过：" + error.getDefaultMessage();
                errorList.add(message);
            }
        }
        if (errorList.size() == 0) {
            log.error("参数校验异常");
            return ApiRestResponse.error("A0002");
        }
        log.error(errorList.toString());
        // A0002-参数校验异常
        return ApiRestResponse.error("A0002",result.getObjectName()+ ":" + errorList.toString());
    }

}
