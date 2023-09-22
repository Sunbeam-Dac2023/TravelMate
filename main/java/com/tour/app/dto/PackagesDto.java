package com.tour.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Hotels;
import com.tour.app.pojo.PkgActivity;
import com.tour.app.pojo.Rooms;

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
public class PackagesDto {
	private int packageId;
	private String pkgName;
	private String pkgType;
	private String destinationCity;
	private String imgPath;
	private int noOfNights;
	private double packageBaseRate;

	private boolean flightStatus;
	private boolean dropStatus;
	private boolean packageActiveStatus;

	@JsonIgnoreProperties(value = "rooms")
	private Hotels hotel;

	@JsonIgnoreProperties(value = "hotel")
	private Rooms room;

	@JsonIgnoreProperties(value = "packages")
	private List<PkgActivity> pkgActivity;

}
