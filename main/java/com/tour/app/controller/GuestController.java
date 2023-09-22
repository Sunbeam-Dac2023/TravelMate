package com.tour.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.GuestsDto;
import com.tour.app.dto.RoomsDto;
import com.tour.app.pojo.Guests;
import com.tour.app.service.IGuestService;

@RestController
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IGuestService guestService;

	// To Add New Guest.
	@PostMapping("/{bookingId}")
	public ResponseEntity<GuestsDto> addGuest(@Valid @RequestBody GuestsDto guestsDto,
			@PathVariable Integer bookingId) {
		System.out.println("Inside add guest");
		Guests guest = this.modelMapper.map(guestsDto, Guests.class);
		Guests newlyCreatedGuest = this.guestService.addGuest(guest, guest.getUser().getUserId(),
				guestsDto.getBooking().getBookingId());
		return new ResponseEntity<GuestsDto>(this.modelMapper.map(newlyCreatedGuest, GuestsDto.class),
				HttpStatus.CREATED);
	}

	// To Get All guests of particular booking.
	@GetMapping("/{bookingId}")
	public ResponseEntity<List<GuestsDto>> getGuestListOfBooking(@PathVariable Integer bookingId) {
		System.out.println("Inside Get Guest List Of Perticular Booking");
		return ResponseEntity.ok(this.guestService.getGuestListOfBooking(bookingId));
	}

}
