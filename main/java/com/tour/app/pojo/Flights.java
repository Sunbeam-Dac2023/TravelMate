package com.tour.app.pojo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Flights")
public class Flights {

	@Id
	@Column(name = "FlightId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightId;

	@Column(name = "FlightCode", length = 50, unique = true)
	private String flightCode;

	@Column(name = "CompanyName", length = 50)
	private String companyName;

	@Column(name = "FromCity", length = 50)
	private String fromCity;
	
	@Column(name = "DestCity", length = 50)
	private String destCity;

	@Column(name = "FlightDuration") // in Minutes
	private int flightDuration;

	@Column(name = "DepartDateTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime departDateTime;

	@Column(name = "ArrivalDateTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime arrivalDateTime;

	@Column(name = "TotalSeats")
	private int totalSeats;

	@Column(name = "AvailableSeats")
	private int availableSeats;

	@Column(name = "RatePerSeat")
	private double ratePerSeat;

	@Column(name = "MaxCabinBagWt")
	private double maxCabinBagWt;

	@Column(name = "MaxCheckInBagWt")
	private double maxCheckInBagWt;

	public Flights(int flightId, String companyName, String fromCity, String destCity, int flightDuration,
			LocalDateTime departDateTime, LocalDateTime arrivalDateTime, int totalSeats, int availableSeats,
			double ratePerSeat, double maxCabinBagWt, double maxCheckInBagWt) {
		super();
		this.flightId = flightId;
		this.companyName = companyName;
		this.fromCity = fromCity;
		this.destCity = destCity;
		this.flightDuration = flightDuration;
		this.departDateTime = departDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.ratePerSeat = ratePerSeat;
		this.maxCabinBagWt = maxCabinBagWt;
		this.maxCheckInBagWt = maxCheckInBagWt;
	}

}
