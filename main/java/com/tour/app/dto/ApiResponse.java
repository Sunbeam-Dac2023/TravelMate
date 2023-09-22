package com.tour.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String message;
	private boolean success;
	private LocalDateTime timestamp;

	public ApiResponse(String message) {
		super();
		this.message = message;
		timestamp = LocalDateTime.now();
	}
	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
		timestamp = LocalDateTime.now();
	}
}