package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.UsersDto;

public interface IUserService {
	UsersDto addUser(UsersDto userDto);

	UsersDto updateUser(UsersDto userDto);

	UsersDto deactivateUser(Integer userId);
	
	UsersDto reactivateUser(Integer userId);

	List<UsersDto> getAllUsers();

	UsersDto getUserByUsername(String email);
	
	UsersDto getUser(Integer userId); 
}
