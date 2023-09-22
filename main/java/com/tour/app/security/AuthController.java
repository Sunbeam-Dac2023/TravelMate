package com.tour.app.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.JwtAuthResponse;
import com.tour.app.dto.UsersDto;
import com.tour.app.pojo.Credentials;
import com.tour.app.service.IUserService;
import com.tour.app.service.impl.UserServiceImpl;

@CrossOrigin
@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtAuthResponse> authenticate(@RequestBody Credentials cred) {
		System.out.println("Authenticating: " + cred);
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword());
			System.out.println("BEFORE: " + auth);
			auth = authManager.authenticate(auth);
			System.out.println("AFTER: " + auth);
			User user = (User)auth.getPrincipal();
			String token = jwtUtils.generateToken(user.getUsername());
			JwtAuthResponse response = new JwtAuthResponse();
			UsersDto userDetails = this.userService.getUserByUsername(cred.getEmail());
			response.setToken(token);
			response.setUser(this.mapper.map(userDetails, UsersDto.class));
			return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		}catch (BadCredentialsException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
}
