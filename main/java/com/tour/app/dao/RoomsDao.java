package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.Rooms;

@Repository
public interface RoomsDao extends JpaRepository<Rooms, Integer>{
	
	List<Rooms> findByHotel(Hotels hotel);

}
