package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.FlightDao;
import com.tour.app.dto.FlightDto;
import com.tour.app.dto.HotelDto;
import com.tour.app.pojo.Flights;
import com.tour.app.pojo.Hotels;
import com.tour.app.service.IFlightService;

@Service
@Transactional
public class FlightServiceImpl implements IFlightService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FlightDao flightDao;

	@Override
	public FlightDto addFlight(FlightDto flightDto) {
		Flights flight = this.modelMapper.map(flightDto, Flights.class);
		Flights newlyAdddedFlight = this.flightDao.save(flight);
		return this.modelMapper.map(newlyAdddedFlight, FlightDto.class);
	}

	@Override
	public FlightDto updateFlight(FlightDto updatedFlightDetails) {
		Flights flight = this.flightDao.findById(updatedFlightDetails.getFlightId())
				.orElseThrow((() -> new ResourceNotFoundException("Flight Not found")));

		flight.setArrivalDateTime(updatedFlightDetails.getArrivalDateTime());
		flight.setDepartDateTime(updatedFlightDetails.getDepartDateTime());
		flight.setFlightCode(updatedFlightDetails.getFlightCode());
		flight.setFlightDuration(updatedFlightDetails.getFlightDuration());

		Flights newlyUpdatedFlight = this.flightDao.save(flight);
		return this.modelMapper.map(newlyUpdatedFlight, FlightDto.class);
	}

	@Override
	public FlightDto getFlightById(Integer flightId) {
		Flights flight = this.flightDao.findById(flightId)
				.orElseThrow((() -> new ResourceNotFoundException("Flight Not found")));
		return this.modelMapper.map(flight, FlightDto.class);
	}

	@Override
	public List<FlightDto> getAllFlight() {
		List<Flights> flights = this.flightDao.findAll();
		List<FlightDto> allFlights = flights.stream().map(flight -> this.modelMapper.map(flight, FlightDto.class))
				.collect(Collectors.toList());
		return allFlights;
	}

	@Override
	public List<FlightDto> getHotelBySrcDestCity(String fromCity, String destCity) {
		// TODO Auto-generated method stub
		List<Flights> flights = this.flightDao.findByFromCityAndDestCity(fromCity,destCity);
		List<FlightDto> allFlights = flights.stream().map(hotel -> this.modelMapper.map(hotel, FlightDto.class))
				.collect(Collectors.toList());
		return allFlights;
	}

}
