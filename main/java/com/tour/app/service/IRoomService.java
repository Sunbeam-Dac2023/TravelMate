package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.RoomsDto;

public interface IRoomService {

	RoomsDto createRoom(RoomsDto roomDto, Integer hotelId);

	RoomsDto updateRoom(RoomsDto roomDto);

	void deleteRoom(Integer hotelId);

	RoomsDto getRoomById(Integer roomId);

	RoomsDto deactivateRoom(Integer roomId);

	List<RoomsDto> getAllRoomsByHotel(Integer hotelId);
	
	RoomsDto setImgPath(Integer roomId,String fpath);


}
