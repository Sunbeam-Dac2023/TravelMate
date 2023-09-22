package com.tour.app.dto;

import com.tour.app.pojo.Bookings;

public class BookedPkgDetailsDto {
	private int bookedPkgId;

	private String pkgName;

	private String pkgType;

	private String destinationCity;

	private int noOfNights;

	private boolean flightStatus;

	private boolean dropStatus;

	private double packageBaseRate;

	private double bookingRoomRate;

	private Bookings booking;
}
