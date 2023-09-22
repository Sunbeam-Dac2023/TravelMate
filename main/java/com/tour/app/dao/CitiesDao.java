package com.tour.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.app.pojo.Cities;

public interface CitiesDao extends JpaRepository<Cities, Integer> {

}
