package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.HotelsDao;
import com.tour.app.dao.RoomsDao;
import com.tour.app.dto.HotelDto;
import com.tour.app.dto.RoomsDto;
import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.Rooms;
import com.tour.app.service.IRoomService;

@Service
@Transactional
public class RoomServiceImpl implements IRoomService {

	@Autowired
	private RoomsDao roomsDao;

	@Autowired
	private HotelsDao hotelDao;

	@Autowired
	private ModelMapper modelMapper;

	// Create Room
	@Override
	public RoomsDto createRoom(RoomsDto roomDto, Integer hotelId) {

		Hotels hotel = this.hotelDao.findById(hotelId)
				.orElseThrow((() -> new ResourceNotFoundException("Hotel Not found")));
		Rooms room = this.modelMapper.map(roomDto, Rooms.class);
		room.setHotel(hotel);
		Rooms addedRoom = this.roomsDao.save(room);
		return this.modelMapper.map(addedRoom, RoomsDto.class);
	}

	// Update Room
	@Override
	public RoomsDto updateRoom(RoomsDto updatedRoomDto) {

		Rooms newRoom = this.roomsDao.findById(updatedRoomDto.getRoomId())
				.orElseThrow((() -> new ResourceNotFoundException("Room Not found")));

		newRoom.setRoomType(updatedRoomDto.getRoomType());
		newRoom.setRoomRate(updatedRoomDto.getRoomRate());
		newRoom.setRoomCapacity(updatedRoomDto.getRoomCapacity());
		newRoom.setAvailableRooms(updatedRoomDto.getAvailableRooms());
		newRoom.setBreakfastIncludedStatus(updatedRoomDto.isBreakfastIncludedStatus());
		newRoom.setRoomActiveStatus(updatedRoomDto.isRoomActiveStatus());

		Rooms updatedRoom = this.roomsDao.save(newRoom);
		return this.modelMapper.map(updatedRoom, RoomsDto.class);
	}

	// Delete Room
	@Override
	public void deleteRoom(Integer roomId) {
		Rooms roomToBeDeleted = this.roomsDao.findById(roomId)
				.orElseThrow((() -> new ResourceNotFoundException("Room Not found")));
		this.roomsDao.delete(roomToBeDeleted);
	}

	// Deactivate Rooms
	@Override
	public RoomsDto deactivateRoom(Integer roomId) {
		Rooms room = this.roomsDao.findById(roomId)
				.orElseThrow((() -> new ResourceNotFoundException("Hotel Not found")));
		room.setRoomActiveStatus(false);
		Rooms updatedRoom = this.roomsDao.save(room);
		return this.modelMapper.map(updatedRoom, RoomsDto.class);
	}

	// Get room By Id
	@Override
	public RoomsDto getRoomById(Integer roomId) {
		Rooms room = this.roomsDao.findById(roomId)
				.orElseThrow((() -> new ResourceNotFoundException("Room Not found")));
		return this.modelMapper.map(room, RoomsDto.class);
	}

	// Get All rooms of Specific Hotel
	@Override
	public List<RoomsDto> getAllRoomsByHotel(Integer hotelId) {
		Hotels hotel = this.hotelDao.findById(hotelId)
				.orElseThrow((() -> new ResourceNotFoundException("Hotel Not found")));
		List<Rooms> rooms = this.roomsDao.findByHotel(hotel);

		List<RoomsDto> allRooms = rooms.stream().map(room -> this.modelMapper.map(room, RoomsDto.class))
				.collect(Collectors.toList());
		return allRooms;
	}

	@Override
	public RoomsDto setImgPath(Integer roomId, String fpath) {
		Rooms room = this.roomsDao.findById(roomId)
				.orElseThrow((() -> new ResourceNotFoundException("room Not found")));
		room.setImgPath(fpath);
		Rooms updatedroom = this.roomsDao.save(room);
		return this.modelMapper.map(updatedroom, RoomsDto.class);	}

}
