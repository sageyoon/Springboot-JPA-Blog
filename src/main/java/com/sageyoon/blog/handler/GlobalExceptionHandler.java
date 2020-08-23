package com.sageyoon.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice	// 모든 exception 발생 시 이 class로 들어오기 위함
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String handleArgumentExcetion(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	

}
