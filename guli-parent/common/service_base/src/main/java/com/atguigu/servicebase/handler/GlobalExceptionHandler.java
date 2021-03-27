package com.atguigu.servicebase.handler;


import com.atguigu.commonutils.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



    /**
     * 统一异常处理类
     */


        @ExceptionHandler(Exception.class)
        @ResponseBody
        public R error(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return R.error();
        }

    }


