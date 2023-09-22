package com.tour.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.tour.app.dto.HotelDto;

public interface IHotelService {
	HotelDto createHotel(HotelDto hotelDto);

	HotelDto updateHotel(HotelDto hotelDto);

	HotelDto deactivateHotel(Integer hotelId);

	HotelDto getHotelById(Integer hotelId);

	List<HotelDto> getAllHotels();
	
	HotelDto setImgPath(Integer hotelId,String fpath);

	List<HotelDto> getHotelByDestinationCity(String destinationCity);
}
