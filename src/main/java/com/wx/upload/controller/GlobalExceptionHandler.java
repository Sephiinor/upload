package com.wx.upload.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>Title:GlobalExceptionHandler.java</p >
 * <p>Description:全局异常捕获处理</p >
 *
 * @author Sephinor
 * @time 2019/12/16 19:46
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message",e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }
}
