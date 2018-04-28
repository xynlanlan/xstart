package com.example.start.common.exception.handler;

import com.example.start.common.base.ResultMap;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> ExceptionHandler(Exception e) {
        return ResultMap.failure(ExceptionCode.SYSTEM_ERROR.getCode(), ExceptionCode.SYSTEM_ERROR.getMessage(), e);
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Map<String, Object> ExceptionHandler(ServiceException e) {
        return ResultMap.failure(((ServiceException) e).getCode(), e.getMessage(), e);
    }
}
