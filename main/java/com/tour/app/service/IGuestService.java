package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.GuestsDto;
import com.tour.app.pojo.Guests;

public interface IGuestService {
	Guests addGuest(Guests guest, Integer userId, Integer bookingId);

	List<GuestsDto> getGuestListOfBooking(Integer bookingId);
}
