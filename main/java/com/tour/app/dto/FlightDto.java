package com.tour.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class FlightDto {
	private int flightId;
	private String flightCode;
	private String companyName;
	private String fromCity;
	private String destCity;
	private int flightDuration;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime departDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime arrivalDateTime;

	private int totalSeats;
	private int availableSeats;
	private double ratePerSeat;
	private double maxCabinBagWt;
	private double maxCheckInBagWt;
}
