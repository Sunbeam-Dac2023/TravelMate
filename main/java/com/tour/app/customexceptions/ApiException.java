package com.tour.app.customexceptions;

public class ApiException extends RuntimeException {

	public ApiException(String message) {
		super(message);
	}

	public ApiException() {
		super();
	}
}
