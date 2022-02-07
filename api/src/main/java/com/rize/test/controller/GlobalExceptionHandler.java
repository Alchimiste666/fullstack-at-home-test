package com.rize.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rize.test.dto.ApiError;
import com.rize.test.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();

		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation failed. " + exception.getLocalizedMessage(), errors);
		return handleExceptionInternal(exception, apiError, headers, apiError.getStatus(), request);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(exception.getLocalizedMessage(), details);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception,
			WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Server error. " + exception.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception,
			WebRequest request) {
		String error = exception.getName() + " should be of type " + exception.getRequiredType().getName();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}