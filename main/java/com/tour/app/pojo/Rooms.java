package com.tour.app.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Rooms")
@ToString(exclude = "hotel")
public class Rooms {
	
	@Id
	@Column(name = "RoomId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int roomId;
	
	@ManyToOne
	@JoinColumn(name = "HotelId")
	private Hotels hotel;
	
	@Column(name="RoomType", length = 50)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Room Type must be supplied!")
	private RoomType roomType;
	
	@Column(name="RoomRate")
	private double roomRate;
	
	@Column(name="RoomCapacity")
	private int roomCapacity;
	
	@Column(name = "ImgPath", length = 300)
	private String ImgPath;
	
	@Column(name="AvailableRooms")
	private int availableRooms;
	
	@Column(name="IsBreakfastIncluded", columnDefinition = "boolean default false")
	private boolean breakfastIncludedStatus;
	
	@Column(name="Is_AC", columnDefinition = "boolean default false")
	private boolean acNonAcStatus;
	
	@Column(name="IsRoomActive", columnDefinition = "boolean default false")
	private boolean roomActiveStatus;
	
}
