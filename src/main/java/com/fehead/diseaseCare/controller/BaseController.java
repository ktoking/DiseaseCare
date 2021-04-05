package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    // 定义exceptionHandler来解决controller层中未被吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerExcepetion(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException BusinessException = (BusinessException)ex;
            responseData.put("errorCode", BusinessException.getErrorCode());
            responseData.put("errorMsg", BusinessException.getErrorMsg());
        } else if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)ex;
            StringBuilder b = new StringBuilder();
            for (ObjectError error : methodArgumentNotValidException.getBindingResult().getAllErrors()) {
                //获取校验的信息
                b.append(error.getDefaultMessage()).append(" ");
            }
            b.deleteCharAt(b.length()-1);

            responseData.put("errorCode",11000);
            responseData.put("errorMsg",b.toString());
        } else if(ex instanceof BindException){
            BindException bindException = (BindException)ex;
            StringBuilder b = new StringBuilder();
            for (ObjectError error : bindException.getBindingResult().getAllErrors()) {
                //获取校验的信息
                b.append(error.getDefaultMessage()).append(" ");
            }
            b.deleteCharAt(b.length()-1);
            responseData.put("errorCode",11000);
            responseData.put("errorMsg",b.toString());
        } else {
            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMsg", EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }

        return CommonReturnType.creat(responseData,"fail");
    }
}
