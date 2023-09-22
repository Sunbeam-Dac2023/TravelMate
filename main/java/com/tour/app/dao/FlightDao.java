package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tour.app.pojo.Flights;
import com.tour.app.pojo.Hotels;

public interface FlightDao extends JpaRepository<Flights, Integer>  {
	
	List<Flights> findByFromCityAndDestCity(String fromCity, String destinationCity);

}
