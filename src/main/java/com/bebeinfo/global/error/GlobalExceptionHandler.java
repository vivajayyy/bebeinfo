package com.bebeinfo.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model, HttpServletRequest request) {
        logger.error("Request: {} raised {}", request.getRequestURL(), e);
        
        model.addAttribute("exception", e);
        model.addAttribute("url", request.getRequestURL());
        model.addAttribute("message", e.getMessage());
        
        return "error/error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model, HttpServletRequest request) {
        logger.error("Request: {} raised {}", request.getRequestURL(), e);
        
        model.addAttribute("exception", e);
        model.addAttribute("url", request.getRequestURL());
        model.addAttribute("message", e.getMessage());
        
        return "error/400";
    }

    // 추가적인 예외 핸들러들을 필요에 따라 구현
} 