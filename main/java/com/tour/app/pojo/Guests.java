package com.tour.app.pojo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Guests")
public class Guests {
	@Id
	@Column(name = "GuestId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int guestId;

	@ManyToOne
	@JoinColumn(name="Booking_Id", nullable=false)
	@JsonIgnoreProperties(value="user, payments")
	private Bookings booking;

	@ManyToOne
	@JoinColumn(name = "User_Id", nullable = false) // To specify FK
	@JsonIgnoreProperties(value = "bookings")
	private Users user;

	@Column(name = "GuestFirstName", length = 50)
	private String guestFirstName;

	@Column(name = "GuestLastName", length = 50)
	private String guestLastName;

	@Column(name = "DOB")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(name = "Gender")
	private String gender;

	public Guests(int guestId, Bookings booking, Users user, String guestFirstName, String guestLastName, LocalDate dob,
			String gender) {
		super();
		this.guestId = guestId;
		this.booking = booking;
		this.user = user;
		this.guestFirstName = guestFirstName;
		this.guestLastName = guestLastName;
		this.dob = dob;
		this.gender = gender;
	}

}
