package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.BookingDao;
import com.tour.app.dao.UserDao;
import com.tour.app.dto.BookingDto;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Payments;
import com.tour.app.pojo.Users;
import com.tour.app.service.IBookingService;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserDao userDao;

	@Override
	public BookingDto addBooking(BookingDto bookingDto) {
		Bookings booking = this.modelMapper.map(bookingDto, Bookings.class);
		Bookings newBooking = this.bookingDao.save(booking);
		return this.modelMapper.map(newBooking, BookingDto.class);
	}

	@Override
	public BookingDto getBookingById(Integer bookingId) {
		Bookings booking = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not found")));
		return this.modelMapper.map(booking, BookingDto.class);
	}

	@Override
	public List<BookingDto> getAllBookings() {
		List<Bookings> bookings = this.bookingDao.findAll();
		List<BookingDto> allBookings = bookings.stream().map(booking -> this.modelMapper.map(booking, BookingDto.class))
				.collect(Collectors.toList());
		return allBookings;
	}
	
	@Override
	public BookingDto updateBookingStatus(Integer bookingId, Integer newBookingStatus) {
		Bookings booking = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not found")));
		booking.setBookingStatus(-1);
		Bookings newBooking = this.bookingDao.save(booking);
		return this.modelMapper.map(newBooking, BookingDto.class);
	}
	
	@Override
	public void updatePaymentId(Integer bookingId, Payments payment) {
		Bookings booking = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not found")));
		booking.setPayment(payment);
		Bookings newBooking = this.bookingDao.save(booking);
//		return this.modelMapper.map(newBooking, BookingDto.class);
	}

	@Override
	public List<BookingDto> showBookingById(Integer userId) {
		// TODO Auto-generated method stub
		Users user = this.userDao.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User Not Found")));
		List<Bookings> bu = this.bookingDao.findByUser(user);
		List<BookingDto> bto = bu.stream().map(b->this.modelMapper.map(b, BookingDto.class)).collect(Collectors.toList());
		return bto;
	}
	
	

}
