package com.tour.app.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Bookings")
public class Bookings {

	@Id
	@Column(name = "BookingId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	@Column(name = "FromCity")
	@JsonFormat(pattern = "yyyy-MM-dd")

	private String fromCity;

	@Column(name = "TourStartDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate tourStartDate;

	@Column(name = "TourEndDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate tourEndDate;

	@Column(name = "NoOfGuests")
	private int noOfGuests;

	// 0 : Upcoming, 1: Completed, -1 Cancelled 
	@Column(name = "BookingStatus")
	private int bookingStatus;

	// Relations

	@ManyToOne()
	@JoinColumn(name = "UserId") // Foreign key
//	@JsonIgnoreProperties(value = "guests, bookings")
	@JsonIgnore
	private Users user;

	@OneToOne()
	@JoinColumn(name = "BookedPkgId") // Foreign key
//	@JsonIgnoreProperties(value = "bookings")
	@JsonIgnore
	private BookedPkgDetails bookedPackageDetails;
	
	@OneToMany(mappedBy = "booking")
	@JsonIgnore
	private List<Guests> guests = new ArrayList<>();
	
	@OneToOne()
	@JoinColumn(name = "HotelId") // Foreign key
//	@JsonIgnoreProperties(value = "rooms")
	@JsonIgnore
	private Hotels hotel;

	@OneToOne()
	@JoinColumn(name = "RoomId") // Foreign key
//	@JsonIgnoreProperties(value = "hotel")
	@JsonIgnore
	private Rooms room;

	@OneToOne()
	@JoinColumn(name = "DepartureFlightId") // Foreign key
	private Flights departureFlight;

	@OneToOne()
	@JoinColumn(name = "ReturnFlightId") // Foreign key
	private Flights returnFlight;

	@OneToOne()
	@JoinColumn(name = "PaymentId" , nullable = true) // Foreign key
	private Payments payment;

	// List of Activities under this booking
	@OneToMany(mappedBy = "bookings", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookedActivities> bookedActivities = new ArrayList<>();

	public Bookings(int bookingId, String fromCity, LocalDate tourStartDate, LocalDate tourEndDate, int noOfGuests,
			int bookingStatus, Users user, BookedPkgDetails bookedPackageDetails, Hotels hotel, Rooms room,
			Flights departureFlight, Flights returnFlight, Payments payment) {
		super();
		this.bookingId = bookingId;
		this.fromCity = fromCity;
		this.tourStartDate = tourStartDate;
		this.tourEndDate = tourEndDate;
		this.noOfGuests = noOfGuests;
		this.bookingStatus = bookingStatus;
		this.user = user;
		this.bookedPackageDetails = bookedPackageDetails;
		this.hotel = hotel;
		this.room = room;
		this.departureFlight = departureFlight;
		this.returnFlight = returnFlight;
//		this.payment = payment;
	}
	
	
	
	

}
