package com.gangbb.common.exception;

import com.gangbb.common.model.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
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
     * HTTPException 网络异常捕捉
     */
    @ExceptionHandler(HTTPException.class)
    public ApiRestResponse exception(HTTPException e)
    {
        log.error("捕捉异常类型：HTTPException");
        //A0003--HTTP请求错误
        return ApiRestResponse.error("A0003", e.getMessage());
    }

    /**
     * ServletException 网络请求异常捕捉
     */
    @ExceptionHandler(ServletException.class)
    public ApiRestResponse exception(ServletException e)
    {
        log.error("捕捉异常类型：ServletException");
        //A0003--HTTP请求错误
        return ApiRestResponse.error("A0003",e.getMessage());
    }

    /**
     * HttpRequestMethodNotSupportedException 网络请求方法异常捕捉
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiRestResponse exception(HttpRequestMethodNotSupportedException e)
    {
        log.error("捕捉异常类型：HttpRequestMethodNotSupportedException");
        //A0003--HTTP请求错误
        return ApiRestResponse.error("A0003",e.getMessage());
    }


    /**
     * HttpMessageNotReadableException 请求参数获取异常(一般是用户没填RequestBody)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiRestResponse exception(HttpMessageNotReadableException e){
        log.error("捕捉异常类型：HttpMessageNotReadableException");
        return ApiRestResponse.error("A0003", e.getMessage());
    }

    /**e
     * MethodArgumentTypeMismatchException 参数类型转换异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiRestResponse exception(MethodArgumentTypeMismatchException e){
        log.error("捕捉异常类型：MethodArgumentTypeMismatchException");
        String msg = "参数<" + e.getName() + ">校验失败：" +  e.getMessage();
        return ApiRestResponse.error("A0002", msg);
    }

    /**
     * BindException
     * - @Valid 参数校验异常捕捉
     */
    @ExceptionHandler(BindException.class)
    public ApiRestResponse exception(BindException e)
    {
        log.error("捕捉异常类型：BindException");
        return handleBindingResult(e.getBindingResult());
    }

    /**
     * MethodArgumentNotValidException
     * - @Valid @RequestBody 参数校验异常捕捉
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiRestResponse exception(MethodArgumentNotValidException e){
        log.error("捕捉异常类型：MethodArgumentNotValidException");
        return handleBindingResult(e.getBindingResult());
    }

    /**
     * MissingServletRequestParameterException
     * - @Valid @RequestParam 参数校验异常捕捉
     *
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ApiRestResponse exception(MissingServletRequestParameterException e){
        log.error("捕捉异常类型：MissingServletRequestParameterException");
        return ApiRestResponse.error("A0002", e.getMessage());
    }

    /**
     * ConstraintViolationException
     * - @Valid @RequestParam 参数校验异常捕捉
     *
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ApiRestResponse exception(ConstraintViolationException e){
        log.error("捕捉异常类型：ConstraintViolationException");
        return ApiRestResponse.error("A0002", e.getMessage());
    }

    /**
     * Exception 基类异常捕捉
     */
    @ExceptionHandler(Exception.class)
    public ApiRestResponse exception(Exception e)
    {
        log.error("Exception: ", e);
        // B0001--系统内部错误
        return ApiRestResponse.error("B0001", e.getMessage());
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

            return ApiRestResponse.error("A0002");
        }
        // A0002-参数校验异常
        return ApiRestResponse.error("A0002",result.getObjectName()+ ":" + errorList.toString());
    }

}
