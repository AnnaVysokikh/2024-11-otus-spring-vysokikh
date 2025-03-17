package ru.otus.hw.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error404");
        modelAndView.addObject("errors", e.getMessage());
        return modelAndView;
    }
}