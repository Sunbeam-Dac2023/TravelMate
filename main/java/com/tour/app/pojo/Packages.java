package com.tour.app.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "Packages")
public class Packages {
	@Id
	@Column(name = "package_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int packageId;
	
	@Column(name = "pkgname" , length = 50)
	private String pkgName;
	
	@Column(name = "pkgtype")
	private String pkgType;
	
	@Column(name = "destinationcity", length = 15)
	private String destinationCity;
	
	@Column(name = "ImgPath", length = 300)
	private String ImgPath;
	
	@Column(name = "noofnights")
	private int noOfNights;
	
	@Column(name = "isflight", columnDefinition = "boolean default false")
	private boolean flightStatus;
	
	@Column(name = "isdrop", columnDefinition = "boolean default false")
	private boolean dropStatus;
	
	@Column(name = "packagebaserate")
	private double packageBaseRate;
	
	@Column(name = "ispackageactive", columnDefinition = "boolean default false")
	private boolean packageActiveStatus;
	
	@OneToOne()
	@JoinColumn(name = "HotelId", nullable = false) // Foreign key
	private Hotels hotel;
	
	@OneToOne()
	@JoinColumn(name = "RoomId", nullable = false) // Foreign key
	private Rooms room;
	
	
	@OneToMany(mappedBy = "packages",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<PkgActivity> pkgActivity = new ArrayList<>();

	public Packages(String pkgName, String destinationCity, int noOfNights, boolean flightStatus, boolean dropStatus,
			double packageBaseRate, boolean packageActiveStatus) {
		super();
		this.pkgName = pkgName;
		this.destinationCity = destinationCity;
		this.noOfNights = noOfNights;
		this.flightStatus = flightStatus;
		this.dropStatus = dropStatus;
		this.packageBaseRate = packageBaseRate;
		this.packageActiveStatus = packageActiveStatus;
	}
}
