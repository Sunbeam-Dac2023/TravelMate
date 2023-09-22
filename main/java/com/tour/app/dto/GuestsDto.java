package com.tour.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Users;

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
@JsonInclude(value = Include.NON_NULL)
public class GuestsDto {
	private int guestId;

	@JsonIgnoreProperties(value = "user,bookedPackageDetails,hotel,room, bookedActivities, payment")
	private Bookings booking;

	@JsonIgnoreProperties(value = "guests,bookings")
	private Users user;

	@NotEmpty
	private String guestFirstName;

	@NotEmpty
	private String guestLastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	private String gender;
}
