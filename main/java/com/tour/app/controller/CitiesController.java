package com.tour.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.ApiResponse;
import com.tour.app.dto.CitiesDto;
import com.tour.app.service.ICitiesService;

@RestController
@RequestMapping("/city")
@CrossOrigin("*")
public class CitiesController {
	@Autowired
	private ICitiesService cityService;

	// Add City.
	@PostMapping
	public ResponseEntity<CitiesDto> addCity(@Valid @RequestBody CitiesDto citiesDto) {
		System.out.println("Inside add city");
		CitiesDto newlyAddedCity = this.cityService.addCity(citiesDto);
		return new ResponseEntity<CitiesDto>(newlyAddedCity, HttpStatus.CREATED);
	}

	// Update City.
	@PutMapping
	public ResponseEntity<CitiesDto> updateCity(@Valid @RequestBody CitiesDto citiesDto) {
		System.out.println("Inside Update City");
		CitiesDto updatedCity = this.cityService.updateCity(citiesDto);
		return new ResponseEntity<CitiesDto>(updatedCity, HttpStatus.OK);
	}

	// Delete City.
	@DeleteMapping("/{cityId}")
	public ResponseEntity<ApiResponse> deleteCity(@PathVariable Integer cityId) {
		System.out.println("Inside Delete city");
		this.cityService.deleteCity(cityId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("City deleted successfully"), HttpStatus.OK);
	}

	// Get City By Id.
	@GetMapping("/{cityId}")
	public ResponseEntity<CitiesDto> getRoomById(@Valid @PathVariable Integer cityId) {
		CitiesDto room = this.cityService.getCityById(cityId);
		return new ResponseEntity<CitiesDto>(room, HttpStatus.OK);
	}

	// Get All Cities.
	@GetMapping
	public ResponseEntity<List<CitiesDto>> getAllCities() {
		return ResponseEntity.ok(this.cityService.getAllCities());
	}
}
