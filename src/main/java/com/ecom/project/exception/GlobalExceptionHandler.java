package com.ecom.project.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ecom.project.payload.ApiRespons;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiRespons> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String msg=ex.getMessage();
		ApiRespons apiResponse=new ApiRespons(msg,false);
		return new ResponseEntity<ApiRespons>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiRespons> sqlIntegrityExceptionhandler(SQLIntegrityConstraintViolationException ex){
		String msg=ex.getMessage();
		ApiRespons apiResponse=new ApiRespons(msg,false);
		return new ResponseEntity<ApiRespons>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadUserCredentialException.class)
	public ResponseEntity<ApiRespons> handleBadUserLoginDetailException(BadUserCredentialException ex) {
		ApiRespons apiResonse = new ApiRespons(ex.getMessage(), false);
		return new ResponseEntity<ApiRespons>(apiResonse, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handlesmethodArgsNotValid(MethodArgumentNotValidException ex){
		
		Map<String,String> resp= new HashMap();
		//put the message to map
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldname=((FieldError)error).getField();
			
			String message=error.getDefaultMessage();
			resp.put(fieldname, message);
		});
		return new  ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	}
}
