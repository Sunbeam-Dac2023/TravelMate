package com.tour.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Bookings;

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
@JsonInclude(value = Include.NON_NULL)
public class AnalyticsDto {
	private double totalRevenue;
	private int totalPackages;
	private int newBookings;
	private int cancelledBookings;
	private int soldTours;	
	
	@JsonIgnoreProperties(value = "users, payment")
	private List<Bookings> booking = new ArrayList<>();

}
