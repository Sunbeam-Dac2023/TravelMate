package com.tour.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.RoomType;

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
public class RoomsDto {
	public int roomId;	
	private RoomType roomType;
	private double roomRate;
	private int roomCapacity;
	private String imgPath;
	private int availableRooms;
	private boolean breakfastIncludedStatus;
	private boolean acNonAcStatus;
	private boolean roomActiveStatus;
	
	@JsonIgnoreProperties(value = "rooms")
	private Hotels hotel;
}
