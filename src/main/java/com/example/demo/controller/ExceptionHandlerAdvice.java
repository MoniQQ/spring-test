//package com.example.demo.controller;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.io.IOException;
//
//@ControllerAdvice
//public class ExceptionHandlerAdvice {
//    @ExceptionHandler(ResponseStatusException.class)
//    public void handleException(ResponseStatusException exception, HttpServletResponse response) throws IOException {
//        String msg = exception.getMessage();
//        response.sendError(exception.getStatusCode().value(), msg);
//    }
//}