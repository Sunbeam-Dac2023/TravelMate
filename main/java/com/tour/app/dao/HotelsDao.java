package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.app.pojo.Activities;
import com.tour.app.pojo.Hotels;

public interface HotelsDao extends JpaRepository<Hotels, Integer>{
	List<Hotels> findByDestinationCity(String destinationCity);

}
