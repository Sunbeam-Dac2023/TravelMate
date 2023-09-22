package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Guests;

@Repository
public interface GuestsDao extends JpaRepository<Guests, Long>{
	List<Guests> findByBooking(Bookings booking);
}
