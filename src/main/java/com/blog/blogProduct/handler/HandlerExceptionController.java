package com.blog.blogProduct.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截所有标注有controllerd的页面
@ControllerAdvice
public class HandlerExceptionController {
    //获取日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception{
        //{}相当与占位符 会将后面的信息填充到里面去
        logger.error("Request URL:{},Exception :{}",request.getRequestURL(),e);
        //当有些异常标注了状态码的话,就直接返回
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            //将异常抛出,让SpringBoot处理
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
