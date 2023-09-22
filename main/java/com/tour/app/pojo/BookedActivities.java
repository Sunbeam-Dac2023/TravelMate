package com.tour.app.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BookedActivities")
public class BookedActivities {

	@Id
	@Column(name = "BookedActivityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookedActivityId;
	
	@ManyToOne
	@JoinColumn(name = "ActivityId", nullable = false) // Foreign key
	private Activities activity;

	@ManyToOne
	@JoinColumn(name = "BookingId", nullable = false) // Foreign key
	private Bookings bookings;

	@Column(name = "DayOfActivity")
	private int dayOfActivity;

	@Column(name = "BookingRateOfActivity")
	private double bookingRateOfActivity;

}