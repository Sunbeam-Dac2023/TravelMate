package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.BookingDto;
import com.tour.app.pojo.Payments;

public interface IBookingService {

	BookingDto addBooking(BookingDto bookingDto);

	BookingDto getBookingById(Integer bookingId);

	List<BookingDto> getAllBookings();

	BookingDto updateBookingStatus(Integer bookingId, Integer newBookingStatus);

	void updatePaymentId(Integer bookingId, Payments payment);

	List<BookingDto>  showBookingById(Integer userId);
}
