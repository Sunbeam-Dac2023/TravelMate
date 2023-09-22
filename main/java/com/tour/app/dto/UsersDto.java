package com.tour.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tour.app.pojo.Guests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class UsersDto {
	private int userId;
	
	@NotEmpty
	@Size(min=4, message ="User name min of 4 characters")
	private String name;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotEmpty(message = "Date of birth can't be empty")
	@NotNull(message = "Date of birth can't be empty")
	private LocalDate dob;
	
	@NotEmpty(message ="Gender can't be empty")
	private String gender;
	
	
	private long mobileNumber;
	
	@Email(message = "Email address is not valid")
	@NotEmpty
	private String email;	
	
	@NotEmpty
	@Pattern(regexp = "^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{7,15})$",
	         message = "Password must be min of 7 characters and max of 15 characters and "
	         	+ "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	
	private String role;
	
	private boolean activeStatus;
	
	@JsonIgnoreProperties(value = "user")
	private List<Guests> guests = new ArrayList<>();
//	private List<Guests> guests = new ArrayList<>();
//	
//	private List<Bookings> bookings = new ArrayList<>();
//	
//	private List<Payments> payments = new ArrayList<>();
}
