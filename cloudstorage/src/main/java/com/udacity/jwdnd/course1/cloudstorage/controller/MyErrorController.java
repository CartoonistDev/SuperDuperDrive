package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {

    @GetMapping
    public String renderErrorPage(HttpServletRequest httpRequest){
        Object errorHandler = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (errorHandler != null){
            Integer errorCode = Integer.valueOf(errorHandler.toString());
            if (errorCode == HttpStatus.NOT_FOUND.value()){
                return "404-error";
            } else if (errorCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error-500";
            }
        }

        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
