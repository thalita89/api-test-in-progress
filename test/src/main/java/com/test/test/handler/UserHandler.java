package com.test.test.handler;

import javax.validation.constraints.NotNull;

import com.test.test.validation.BusinessException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handle(BusinessException ex) throws JSONException {
		final JSONObject body = new JSONObject();
		final JSONArray bodyWrapper = new JSONArray();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String msg = ex.getMessage();
		body.put("status", status.value());
		body.put("statusText", status.getReasonPhrase());
		body.put("code", String.format("Error:%s", msg));
		bodyWrapper.put(body);
		return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(@NotNull MethodArgumentNotValidException ex) throws JSONException {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	final String field = ex.getBindingResult().getFieldError().getField();
		final JSONObject body = new JSONObject();
		body.put("status", status.value());
		body.put("statusText", status.getReasonPhrase());
		body.put("code", String.format("%s.is.invalid", field));
		
		final JSONArray bodyWrapper = new JSONArray();
		bodyWrapper.put(body);
		
		return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());
        
    }

	//a verificar
	@ExceptionHandler(ExceptionInInitializerError.class)
    public ResponseEntity<String> handle(@NotNull ExceptionInInitializerError ex) throws JSONException {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	final String field = ex.getMessage();
		final JSONObject body = new JSONObject();
		body.put("status", status.value());
		body.put("statusText", status.getReasonPhrase());
		body.put("code", String.format("%s.is.invalid", field));
		
		final JSONArray bodyWrapper = new JSONArray();
		bodyWrapper.put(body);
		
		return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());  
    }

	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(@NotNull Exception ex) throws JSONException {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	//final String field = ex.getMessage();
		final JSONObject body = new JSONObject();
		body.put("status", status.value());
		body.put("statusText", status.getReasonPhrase());
		//body.put("code", String.format("%s.is.invalid", field));
		
		final JSONArray bodyWrapper = new JSONArray();
		bodyWrapper.put(body);
		
		return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());  
    }
    
    private HttpHeaders defaultHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}

