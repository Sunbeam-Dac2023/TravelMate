package com.tour.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.HotelDto;
import com.tour.app.dto.UsersDto;
import com.tour.app.service.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService userService;

	// To Add New User/Signup User.
	@PostMapping("/signup")
	public ResponseEntity<UsersDto> addUser(@RequestBody UsersDto usersDto) {

		System.out.println("In User");
		System.out.println("User active status " + usersDto.isActiveStatus());
		System.out.println("*******");
		UsersDto newlyCreatedUser = this.userService.addUser(usersDto);
		return new ResponseEntity<UsersDto>(newlyCreatedUser, HttpStatus.CREATED);
	}

	// To Update Particular User Details.
	@PutMapping("{userId}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UsersDto> updateUserDetails(@PathVariable("userId") Integer userId,
			@RequestBody UsersDto usersDto) {
		System.out.println("Inside update user");
		usersDto.setUserId(userId);
		UsersDto updatedUser = this.userService.updateUser(usersDto);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// To Deactivate user
	@PutMapping("/deactivate/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UsersDto> deactivateUser(@Valid @PathVariable Integer userId) {
		UsersDto updatedUser = this.userService.deactivateUser(userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// To Reactivate user.
	@PutMapping("/reactivate/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UsersDto> reactivateUser(@Valid @PathVariable Integer userId) {
		UsersDto updatedUser = this.userService.reactivateUser(userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// View All Users.
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UsersDto>> getAllUsers() {
		System.out.println("Inside All Users");
		List<UsersDto> users = this.userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{userId}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UsersDto> getUser(@PathVariable Integer userId) {
		System.out.println("Inside getUser");
		//List<UsersDto> users = this.userService.getAllUsers();
		UsersDto user=userService.getUser(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
}
