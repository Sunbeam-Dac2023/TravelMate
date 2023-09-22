package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.app.pojo.Packages;

public interface PackageDao extends JpaRepository<Packages, Integer> {
	List<Packages> findByDestinationCity(String destinationCity);
}
