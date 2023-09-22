package com.tour.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.BookedActivities;
import com.tour.app.pojo.BookedPkgDetails;
import com.tour.app.pojo.Flights;
import com.tour.app.pojo.Guests;
import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.Payments;
import com.tour.app.pojo.Rooms;
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
//@JsonInclude(value=Include.NON_NULL)
public class BookingDto {

	private int bookingId;
	private String fromCity;
	private LocalDate tourStartDate;
	private LocalDate tourEndDate;
	private int noOfGuests;
	private String bookingStatus;  	// 0 : Upcoming, 1: Completed, -1 Cancelled
	
	@JsonIgnoreProperties(value = "bookings,password, guests")
	private Users user;

	@JsonIgnoreProperties(value = "user")
	private List<Guests> guests = new ArrayList<>();

	@JsonIgnoreProperties(value = "booking")
	private BookedPkgDetails bookedPackageDetails;

	@JsonIgnoreProperties(value = "rooms")
	private Hotels hotel;

	@JsonIgnoreProperties(value = "hotel")
	private Rooms room;

	private Flights departureFlight;
	private Flights returnFlight;

	@JsonIgnoreProperties(value = "bookings,user")
	private Payments payment;

	@JsonIgnoreProperties(value = "bookings")
	private List<BookedActivities> bookedActivities = new ArrayList<>();
}
