package com.tour.app.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.HotelType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class HotelDto {

	public int hotelId;
	private String hotelName;
	private HotelType hotelType;
	private String hotelAddress;
	private String imgPath;
	private String destinationCity;
	private int pin;

	private boolean wifiStatus;
	private boolean parkingStatus;
	private boolean swimmingPoolStatus;
	private boolean gymStatus;
	private boolean hotelActiveStatus;
	
	private LocalTime checkinTime;
	private LocalTime checkOutTime;
	
	@JsonIgnoreProperties(value = "hotel")
	private List<RoomsDto> rooms = new ArrayList<>();
}
