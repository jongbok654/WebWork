package com.example.spring08.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

//예외 컨트롤러는 @ControllerAdvice 어노테이션을 붙여서 bean 으로 만든다
@Controller
public class ExceptionController {
	
	@ExceptionHandler(PasswordException.class)
	public String password(PasswordException pe) {
		
		return "redirect:/user/edit-password";
	}
}
