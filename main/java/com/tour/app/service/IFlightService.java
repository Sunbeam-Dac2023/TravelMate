package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.FlightDto;

public interface IFlightService {
	FlightDto addFlight(FlightDto flightDto);

	FlightDto updateFlight(FlightDto flightDto);

	FlightDto getFlightById(Integer flightId);

	List<FlightDto> getAllFlight();

	List<FlightDto> getHotelBySrcDestCity(String fromCity, String destCity);
}
