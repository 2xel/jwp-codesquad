package com.codesquad.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AnnotationExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String loginHandler(Exception e) {
		return "redirect:/";
	}
}