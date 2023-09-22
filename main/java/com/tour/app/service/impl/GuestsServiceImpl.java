package com.tour.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.BookingDao;
import com.tour.app.dao.GuestsDao;
import com.tour.app.dao.UserDao;
import com.tour.app.dto.GuestsDto;
import com.tour.app.dto.RoomsDto;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Guests;
import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.Rooms;
import com.tour.app.pojo.Users;
import com.tour.app.service.IGuestService;

@Service
@Transactional
public class GuestsServiceImpl implements IGuestService {

	@Autowired
	private GuestsDao guestsDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	// To Add New Guest
	public Guests addGuest(Guests guest, Integer userId, Integer bookingId) {
		Users user = this.userDao.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User Not Found")));

		Bookings bookings = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not Found")));
		guest.setUser(user);
		guest.setBooking(bookings);
		Guests addedGuest = this.guestsDao.save(guest);
		return addedGuest;
	}

	@Override
	public List<GuestsDto> getGuestListOfBooking(Integer bookingId) {
		Bookings booking = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not Found")));
		List<Guests> guests = this.guestsDao.findByBooking(booking);

		List<GuestsDto> allGuests = guests.stream().map(guest -> this.modelMapper.map(guest, GuestsDto.class))
				.collect(Collectors.toList());
		return allGuests;

	}

}
