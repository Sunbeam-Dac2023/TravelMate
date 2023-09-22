package com.tour.app.pojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Hotels")
@ToString(exclude = "rooms")
public class Hotels {

	@Id
	@Column(name = "HotelId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int hotelId;
	
	@Column(name = "HotelName", length = 300)
	private String hotelName;

	@Column(name = "HotelType", length = 50)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Hotel Type must be supplied!")
	private HotelType hotelType;
	
	@Column(name = "ImgPath", length = 300)
	private String ImgPath;
	
	@Column(name = "HotelAddress", length = 300)
	private String hotelAddress;

	@Column(name = "DestinationCity", length = 50)
	private String destinationCity;

	@Column(name = "HotelPin")
	private int pin;

	@Column(name = "IsWifi", columnDefinition = "boolean default false")
	private boolean wifiStatus;

	@Column(name = "IsParking", columnDefinition = "boolean default false")
	private boolean parkingStatus;

	@Column(name = "IsSwimmingPool", columnDefinition = "boolean default false")
	private boolean swimmingPoolStatus;

	@Column(name = "IsGym", columnDefinition = "boolean default false")
	private boolean gymStatus;

	@Column(name = "CheckinTime")
	private LocalTime checkinTime;

	@Column(name = "CheckOutTime")
	private LocalTime checkOutTime;
	
	@Column(name = "IsHotelActive", columnDefinition = "boolean default false")
	private boolean hotelActiveStatus;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Rooms> rooms = new ArrayList<>();
}
