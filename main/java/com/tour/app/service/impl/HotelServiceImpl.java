package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.HotelsDao;
import com.tour.app.dto.HotelDto;
import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.Rooms;
import com.tour.app.service.IHotelService;
import com.tour.app.service.IRoomService;

@Service
@Transactional
public class HotelServiceImpl implements IHotelService {

	@Override
	public HotelDto setImgPath(Integer hotelId, String fpath) {
		Hotels hotel = this.hotelsDao.findById(hotelId)
				.orElseThrow((() -> new ResourceNotFoundException("Hotel Not found")));
		hotel.setImgPath(fpath);
		Hotels updatedHotel = this.hotelsDao.save(hotel);
		return this.modelMapper.map(updatedHotel, HotelDto.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HotelsDao hotelsDao;

	@Autowired
	private IRoomService roomService;

	// Create Hotel
	@Override
	public HotelDto createHotel(HotelDto hotelDto) {
		Hotels hotel = this.modelMapper.map(hotelDto, Hotels.class);
		hotel.getRooms().forEach((room) -> {
			room.setHotel(hotel);
		});
		Hotels newlyCreatedHotel = this.hotelsDao.save(hotel);
		return this.modelMapper.map(newlyCreatedHotel, HotelDto.class);
	}

	// Update Hotel
	@Override
	public HotelDto updateHotel(HotelDto hotelDto) {
		Hotels hotel = this.modelMapper.map(hotelDto, Hotels.class);
		hotel.getRooms().forEach((room) -> {
			room.setHotel(hotel);
		});
		Hotels updatedHotel = this.hotelsDao.save(hotel);
		return this.modelMapper.map(updatedHotel, HotelDto.class);
	}

	// Deactivate hotel
	@Override
	public HotelDto deactivateHotel(Integer hotelId) {
		Hotels hotel = this.hotelsDao.findById(hotelId)
				.orElseThrow((() -> new ResourceNotFoundException("Hotel Not found")));
		hotel.setHotelActiveStatus(false);

		// Deactivate all rooms of hotel
		List<Rooms> rooms = hotel.getRooms();
		rooms.forEach(room -> {
			this.roomService.deactivateRoom(room.getRoomId());
			room.setHotel(hotel);
		});

		Hotels updatedHotel = this.hotelsDao.save(hotel);
		return this.modelMapper.map(updatedHotel, HotelDto.class);
	}

	// Get Hotel By ID
	@Override
	public HotelDto getHotelById(Integer hotelId) {
		Hotels hotel = this.hotelsDao.findById(hotelId)
				.orElseThrow((() -> new ResourceNotFoundException("Hotel Not found")));
		return this.modelMapper.map(hotel, HotelDto.class);
	}

	// Get All Hotels
	@Override
	public List<HotelDto> getAllHotels() {
		List<Hotels> hotels = this.hotelsDao.findAll();
		List<HotelDto> allHotels = hotels.stream().map(hotel -> this.modelMapper.map(hotel, HotelDto.class))
				.collect(Collectors.toList());
		return allHotels;
	}

	@Override
	public List<HotelDto> getHotelByDestinationCity(String destinationCity) {
		// TODO Auto-generated method stub
		List<Hotels> hotels = this.hotelsDao.findByDestinationCity(destinationCity);
		List<HotelDto> allHotels = hotels.stream().map(hotel -> this.modelMapper.map(hotel, HotelDto.class))
				.collect(Collectors.toList());
		return allHotels;
	}
	
	
}
