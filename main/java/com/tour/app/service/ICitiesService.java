package com.tour.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tour.app.dto.CitiesDto;

public interface ICitiesService {
	CitiesDto addCity(CitiesDto citiesDto);

	CitiesDto updateCity(CitiesDto citiesDto);

	void deleteCity(Integer cityId);

	CitiesDto getCityById(Integer cityId);

	List<CitiesDto> getAllCities();

}
