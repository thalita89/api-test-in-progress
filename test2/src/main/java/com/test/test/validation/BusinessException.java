package com.test.test.validation;

import org.json.JSONException;

public class BusinessException extends JSONException {//checked
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
}
