package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.Activities;

@Repository
public interface ActivitiesDao extends JpaRepository<Activities, Integer> {
	List<Activities> findByDestinationCity(String destinationCity);

}
