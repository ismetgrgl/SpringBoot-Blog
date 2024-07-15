package org.ismetg.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Bu sınıf tüm controller sınıfları için merkezi bir şekilde hata yönetimi sağlayacaktır.
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)// RuntimeException Hata yakalayıcı bir metod olduğunu belirtmek için.
	public ResponseEntity<String> handleException() {
		return ResponseEntity.badRequest().body("Uygulamada RunTime Exception oluştu................");
	}


	@ExceptionHandler(BlogException.class)
	public ResponseEntity<ErrorMessage> handleDemoException(BlogException ex) {
		ErrorType errorType = ex.getErrorType();
		return new ResponseEntity(createErrorMessage(ex),
		                          errorType.getHttpStatus());
	}
	
	private ErrorMessage createErrorMessage(BlogException ex) {
		return ErrorMessage.builder()
		                   .code(ex.getErrorType().getCode())
		                   .message(ex.getMessage())
		                   .build();
	}

	
	
	
}