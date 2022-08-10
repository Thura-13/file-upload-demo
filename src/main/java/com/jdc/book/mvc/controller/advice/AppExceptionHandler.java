package com.jdc.book.mvc.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.jdc.book.root.service.FileUploadAppException;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler
	ModelAndView handle(FileUploadAppException e) {
		var model = new ModelAndView("home");
		model.getModel().put("uploadMessage", "Fails to upload : %s".formatted(e.getMessage()));
		return model;
	}
}
