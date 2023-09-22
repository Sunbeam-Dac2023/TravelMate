package com.tour.app.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BookedPackagesDetails")
public class BookedPkgDetails {

	@Id
	@Column(name = "BookedPkg_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookedPkgId;

	@Column(name = "PkgName", length = 150)
	private String pkgName;

	@Column(name = "PkgType", length = 50)
	private String pkgType;

	@Column(name = "DestinationCity", length = 50)
	private String destinationCity;

	@Column(name = "NoOfNights")
	private int noOfNights;

	@Column(name = "IsFlight", columnDefinition = "boolean default false")
	private boolean flightStatus;

	@Column(name = "IsDrop", columnDefinition = "boolean default false")
	private boolean dropStatus;

	@Column(name = "PackageBaseRate", nullable = false)
	private double packageBaseRate;

	@Column(name = "BookingRoomRate", nullable = false)
	private double bookingRoomRate;

	@OneToOne(mappedBy = "bookedPackageDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Bookings booking;

	public BookedPkgDetails(int bookedPkgId, String pkgName, String pkgType, String destinationCity, int noOfNights,
			boolean flightStatus, boolean dropStatus, double packageBaseRate, double bookingRoomRate) {
		super();
		this.bookedPkgId = bookedPkgId;
		this.pkgName = pkgName;
		this.pkgType = pkgType;
		this.destinationCity = destinationCity;
		this.noOfNights = noOfNights;
		this.flightStatus = flightStatus;
		this.dropStatus = dropStatus;
		this.packageBaseRate = packageBaseRate;
		this.bookingRoomRate = bookingRoomRate;
	}
}
