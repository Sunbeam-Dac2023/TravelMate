package com.tour.app.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

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

import com.tour.app.dto.ApiResponse;
import com.tour.app.dto.FlightDto;
import com.tour.app.dto.HotelDto;
import com.tour.app.service.IFlightService;

@RestController
@RequestMapping("/flight")
@CrossOrigin("*")
public class FlightController {

	@Autowired
	private IFlightService flightService;

	// Create Flight.
	@PostMapping
	public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto FlightDto) {
		FlightDto newlyAdddedFlight = this.flightService.addFlight(FlightDto);
		return new ResponseEntity<FlightDto>(newlyAdddedFlight, HttpStatus.CREATED);
	}

	// Update Flight time(Departure,Arrival),FlightCode,Duration.
	@PutMapping
	public ResponseEntity<?> updateFlight(@RequestBody FlightDto flightDto) {

		FlightDto actualFlightDto = this.flightService.getFlightById(flightDto.getFlightId());
		long noOfHours = Duration.between(LocalDateTime.now(), actualFlightDto.getDepartDateTime()).toHours();

		// IFF Duration between departure flight time & time when request to update
		// raised is greater than 24Hrs
		// then only allow to update
		if (noOfHours >= 24) {
			FlightDto updatedFlight = this.flightService.updateFlight(flightDto);
			return new ResponseEntity<FlightDto>(updatedFlight, HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(
							"Flight details can not be modified as there is less than 24Hrs left for departure"),
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

	// Get Flight By ID.
	@GetMapping("/{flightId}")
	public ResponseEntity<FlightDto> getFlightById(@Valid @PathVariable Integer flightId) {
		FlightDto flight = this.flightService.getFlightById(flightId);
		return new ResponseEntity<FlightDto>(flight, HttpStatus.OK);
	}

	// Get All Flights.
	@GetMapping
	public ResponseEntity<List<FlightDto>> getAllFlight() {
		return ResponseEntity.ok(this.flightService.getAllFlight());
	}

	// Get Hotel By ID with Room details.
	@GetMapping("/getByFromDestcity")
	public ResponseEntity<List<FlightDto>> getHotelBySrcDestCity(@RequestParam(name = "fromCity") String fromCity,
			@RequestParam(name = "destinationCity") String destCity) {
		return ResponseEntity.ok(this.flightService.getHotelBySrcDestCity(fromCity,destCity));
	}

}
