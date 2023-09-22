package com.tour.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.BookedActDao;
import com.tour.app.dao.BookingDao;
import com.tour.app.pojo.Activities;
import com.tour.app.pojo.BookedActivities;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Packages;
import com.tour.app.pojo.PkgActivity;
import com.tour.app.service.IBookedActService;

@Service
@Transactional
public class BookedActServiceImpl implements IBookedActService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private BookedActDao bookedActDao;

	@Override
	public BookedActivities addBookedActivity(Integer bookingId, BookedActivities activity) {
		Bookings bookings = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not Found")));
		activity.setBookings(bookings);
		return this.bookedActDao.save(activity);
	}
}
