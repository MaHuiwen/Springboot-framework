package com.fzm.boot.handler.exception;

import com.fzm.boot.commons.bean.ResResult;
import com.fzm.boot.commons.constant.ResponseEnum;
import com.fzm.boot.commons.exception.ControllerException;
import com.fzm.boot.commons.exception.DaoException;
import com.fzm.boot.commons.exception.ServiceException;
import com.fzm.boot.commons.util.ResResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用异常处理和封装
 * Created by LIUJC9 on 2017/11/1.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResResult handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException ServiceException = (ServiceException) e;
            return ResResultUtil.error(ServiceException.getCode(), ServiceException.getMessage());
        }else if(e instanceof ControllerException){
            ControllerException controllerException = (ControllerException) e;
            return ResResultUtil.error(controllerException.getCode(), controllerException.getMessage());
        }else if(e instanceof DaoException){
            DaoException daoException = (DaoException) e;
            return ResResultUtil.error(daoException.getCode(), daoException.getMessage());
        }else if(e instanceof MethodArgumentNotValidException){
            BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
            FieldError error = result.getFieldError();
            logger.error("request "+ error.getField()+" is  null");
            return ResResultUtil.error(ResponseEnum.COMMONS_PARAM_ERROR.getCode(),error.getDefaultMessage());
        } else {
            logger.error("【系统异常】{}",e);
            return ResResultUtil.error(ResponseEnum.COMMONS_ERROR);
        }
    }
}

