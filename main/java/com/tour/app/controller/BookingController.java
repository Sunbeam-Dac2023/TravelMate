package com.tour.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.AnalyticsDto;
import com.tour.app.dto.BookingDto;
import com.tour.app.dto.PackagesDto;
import com.tour.app.pojo.BookedActivities;
import com.tour.app.pojo.BookedPkgDetails;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Guests;
import com.tour.app.service.IBookedActService;
import com.tour.app.service.IBookedPkgDetails;
import com.tour.app.service.IBookingService;
import com.tour.app.service.IGuestService;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private IBookedPkgDetails bookedPkgDetailsService;

	@Autowired
	private IBookedActService bookedActService;

	@Autowired
	private IGuestService guestService;

	@Autowired
	private ModelMapper modelMapper;

	// Add Booking.
	@PostMapping
	public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto bookingDto) {

		List<BookedActivities> activities = bookingDto.getBookedActivities();
		bookingDto.setBookedActivities(null);

		List<Guests> guests = bookingDto.getGuests();
		bookingDto.setGuests(null);

		// Created entry in BookedPkgDetails tables & fetched new row details with ID.
		BookedPkgDetails bookedPkgDetails = this.bookedPkgDetailsService
				.addBookedPkgdetails(bookingDto.getBookedPackageDetails());
		bookingDto.setBookedPackageDetails(bookedPkgDetails);

		// Add new Booking.
		BookingDto newlyAddedBooking = this.bookingService.addBooking(bookingDto); // Booking ID has been generated

		// Add Guests in DB.
		List<Guests> guestList = new ArrayList<Guests>();
		guests.forEach(guest -> {
			this.guestService.addGuest(guest, bookingDto.getUser().getUserId(), newlyAddedBooking.getBookingId());
			newlyAddedBooking.setGuests(guestList);
		});

		// Set booking ID to all activities & Store entry of activity in BookedActivity table.
		List<BookedActivities> bookedActivities = new ArrayList<BookedActivities>();
		activities.forEach(activity -> {
			activity.setBookings(this.modelMapper.map(newlyAddedBooking, Bookings.class));
			BookedActivities bookedAct = this.bookedActService.addBookedActivity(newlyAddedBooking.getBookingId(),
					activity);
			bookedActivities.add(bookedAct);
		});
		newlyAddedBooking.setBookedActivities(bookedActivities);

		return new ResponseEntity<BookingDto>(newlyAddedBooking, HttpStatus.CREATED);
	}

	// Get Booking By ID.
	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDto> getBookingById(@Valid @PathVariable Integer bookingId) {
		BookingDto booking = this.bookingService.getBookingById(bookingId);
		return new ResponseEntity<BookingDto>(booking, HttpStatus.OK);
	}

	// To Update Particular User Details.
	@PutMapping("/{bookingId}")
	public ResponseEntity<BookingDto> updateBookingStatus(@PathVariable("bookingId") Integer bookingId,
			@RequestParam(name = "newBookingStatus") int newBookingStatus) {
		System.out.println("Inside update Booking status");
		BookingDto udpatedBooking = this.bookingService.updateBookingStatus(bookingId, newBookingStatus);
		return new ResponseEntity<>(udpatedBooking, HttpStatus.OK);
	}

	// Get All Bookings.
	@GetMapping
	public ResponseEntity<List<BookingDto>> getAllBookings() {
		return ResponseEntity.ok(this.bookingService.getAllBookings());
	}
	
	@GetMapping("/getAnalysis")
	public ResponseEntity<AnalyticsDto> getAnalysis() {
		System.out.println("Inside Get Analysis");
//		int noOfCancelledBooking = 0 ;
			
		List<BookingDto> bookings = this.bookingService.getAllBookings();
		AnalyticsDto analysis = new AnalyticsDto();
		analysis.setCancelledBookings(0);
		analysis.setNewBookings(15);
		analysis.setTotalRevenue(1500000);
		analysis.setTotalPackages(5);
		analysis.setSoldTours(50);		
		return new ResponseEntity<>(analysis, HttpStatus.OK);
	}
	@GetMapping("/{userId}/all")
	public ResponseEntity<List<BookingDto>> getAllBookingsById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(this.bookingService.showBookingById(userId));
	}

}
