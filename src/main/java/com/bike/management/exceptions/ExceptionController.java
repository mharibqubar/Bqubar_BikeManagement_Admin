package com.bike.management.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bike.management.configurations.GlobalConstants;
import com.bike.management.models.ApiError;
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ModelAndView handleInvalidCredentials(InvalidCredentialException ex){
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		ApiError error = ApiError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).message(ex.getMessage()).errors(ex.getMessage()).build();
		model.addObject("errMsg", ex.getMessage());
		return model;
		
	}
	
	@ExceptionHandler(SessionExpiredException.class)
	public ModelAndView handleSessionExpired(SessionExpiredException ex){
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		ApiError error = ApiError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.UNAUTHORIZED).message(ex.getMessage()).errors(ex.getMessage()).build();
		model.addObject("errMsg", ex.getMessage());
		return model;
		
	}
	@ExceptionHandler(NoDataFoundException.class)
	public ModelAndView handleNoDataFound(NoDataFoundException ex) {
		ModelAndView mv =  new ModelAndView();
		mv.addObject("errorMsg", ex.getMessage());
		mv.setViewName("bikes/bikes.html");
		return mv;
	}
	
	@ExceptionHandler(UserNotExistException.class)
	public ModelAndView handleUserNotExistException(UserNotExistException ex) {
		ModelAndView mv =  new ModelAndView();
		mv.addObject("errorMsg", ex.getMessage());
		mv.setViewName("bikes/bikes.html");
		return mv;
	}
  
}
