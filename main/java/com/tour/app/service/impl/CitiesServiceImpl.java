package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.CitiesDao;
import com.tour.app.dto.CitiesDto;
import com.tour.app.pojo.Cities;
import com.tour.app.service.ICitiesService;

@Service
@Transactional
public class CitiesServiceImpl implements ICitiesService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CitiesDao citiesDao;

	// Add new city
	@Override
	public CitiesDto addCity(CitiesDto citiesDto) {
		Cities city = this.modelMapper.map(citiesDto, Cities.class);
		Cities newlyAddedCity = this.citiesDao.save(city);
		return this.modelMapper.map(newlyAddedCity, CitiesDto.class);
	}

	// Update city
	@Override
	public CitiesDto updateCity(CitiesDto citiesDto) {
		Cities city = this.citiesDao.findById(citiesDto.getCityId())
				.orElseThrow((() -> new ResourceNotFoundException("City Not found")));
		city.setCityName(citiesDto.getCityName());
		city.setState(citiesDto.getState());
		Cities updatedCity = this.citiesDao.save(city);
		return this.modelMapper.map(updatedCity, CitiesDto.class);
	}

	// Delete City
	@Override
	public void deleteCity(Integer cityId) {
		Cities cityToBeDeleted = this.citiesDao.findById(cityId)
				.orElseThrow((() -> new ResourceNotFoundException("City Not found")));
		this.citiesDao.delete(cityToBeDeleted);
	}

	// Get City By Id
	@Override
	public CitiesDto getCityById(Integer cityId) {
		Cities cities = this.citiesDao.findById(cityId)
				.orElseThrow((() -> new ResourceNotFoundException("City Not found")));
		return this.modelMapper.map(cities, CitiesDto.class);
	}

	// Get All Cities
	@Override
	public List<CitiesDto> getAllCities() {
		List<Cities> cities = this.citiesDao.findAll();
		List<CitiesDto> allCities = cities.stream().map(city -> this.modelMapper.map(city, CitiesDto.class))
				.collect(Collectors.toList());
		return allCities;
	}

}
