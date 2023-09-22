package com.tour.app.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Users")
@ToString(exclude = {"guests","bookings","payments","password"})
public class Users {

	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "Name", length = 50)
	@NotBlank(message = "Full Name is required")
	private String name;

	@Column(name = "DOB")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "MobNo")
	private long mobileNumber;

	@Column(name = "Email_id", length = 25, unique = true)
	@Email(message = "Invalid email format!")
	private String email;

	@Column(name = "Password")
	@JsonIgnore
	//@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid Password !")
	@NotNull(message = "user role must be supplied!")	
	private String password;

	@Column(name = "Role")
	@NotNull(message = "user role must be supplied!")
	private String role;

	@Column(name = "IsActive",columnDefinition = "boolean default false")
	private boolean activeStatus;
	
	//References
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Guests> guests = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Bookings> bookings = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Payments> payments = new ArrayList<>();
	
	
	public User toUser() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
		User user = new User(email, password, 
				Collections.singletonList(authority));
		return user;
	}

	public Users(String name, LocalDate dob, String gender, long mobileNumber, String email, String password, String role,
			boolean activeStatus) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.role = role;
		this.activeStatus = activeStatus;
	}

}
