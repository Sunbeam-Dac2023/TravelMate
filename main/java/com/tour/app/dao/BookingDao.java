package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Users;

@Repository
public interface BookingDao extends JpaRepository<Bookings, Integer> {

	List<Bookings> findByUser(Users user);
}
