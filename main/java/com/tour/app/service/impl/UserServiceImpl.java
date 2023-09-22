package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.UserDao;
import com.tour.app.dto.UsersDto;
import com.tour.app.pojo.Users;
import com.tour.app.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {

//	@Autowired
//	BCryptPasswordEncoder encoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserDao userDao;

	// Get user By emailID
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userDao.findByEmail(email);
		return user.toUser();
	}

	// Get user By emailID
	@Override
	public UsersDto getUserByUsername(String email) {
		Users user = userDao.findByEmail(email);
		return this.modelMapper.map(user, UsersDto.class);
	}

	// To Add New User

	@Override
	public UsersDto addUser(UsersDto userDto) {
		Users user = this.modelMapper.map(userDto, Users.class);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// user.setPassword(encoder.encode(user.getPassword()));
		Users newCreatedUser = this.userDao.save(user);
		return this.modelMapper.map(newCreatedUser, UsersDto.class);
	}

	// To Update Particular User Details
	@Override
	public UsersDto updateUser(UsersDto updatedUserDto) {
		Users newUser = this.userDao.findById(updatedUserDto.getUserId())
				.orElseThrow((() -> new ResourceNotFoundException("User Not Found!")));

		newUser.setName(updatedUserDto.getName());
		newUser.setDob(updatedUserDto.getDob());
		newUser.setGender(updatedUserDto.getGender());
		newUser.setMobileNumber(updatedUserDto.getMobileNumber());
		newUser.setEmail(updatedUserDto.getEmail()); // This is loginID
		//newUser.setPassword(updatedUserDto.getPassword());

		Users updatedUser = this.userDao.save(newUser);
		return this.modelMapper.map(updatedUser, UsersDto.class);
	}

	// To Deactivate user
	@Override
	public UsersDto deactivateUser(Integer userId) {
		Users user = this.userDao.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User Not Found")));
		user.setActiveStatus(false);
		Users updatedUser = this.userDao.save(user);
		return this.modelMapper.map(updatedUser, UsersDto.class);
	}

	// To Deactivate user
	@Override
	public UsersDto reactivateUser(Integer userId) {
		Users user = this.userDao.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User Not Found")));
		user.setActiveStatus(true);
		Users updatedUser = this.userDao.save(user);
		return this.modelMapper.map(updatedUser, UsersDto.class);
	}

	// View All Users
	@Override
	public List<UsersDto> getAllUsers() {
		List<Users> users = this.userDao.findAll();
		List<UsersDto> allUsers = users.stream().map(user -> this.modelMapper.map(user, UsersDto.class))
				.collect(Collectors.toList());
		return allUsers;
	}
	
	//Get User By Id
	@Override
	public UsersDto getUser(Integer userId) {
		Users user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
		return modelMapper.map(user, UsersDto.class);
	}

}
