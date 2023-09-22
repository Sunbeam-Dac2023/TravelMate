package com.tour.app.dto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UsersDto user;
}
