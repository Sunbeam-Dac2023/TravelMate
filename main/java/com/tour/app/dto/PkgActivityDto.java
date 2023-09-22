package com.tour.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Activities;
import com.tour.app.pojo.Packages;

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
public class PkgActivityDto {
	private int pkgActivityId;
	private int dayOfActivity;
	private boolean pkgActStatus;

	@JsonIgnoreProperties(value = "pkgActivity")
	private Activities activities;

	@JsonIgnoreProperties(value = "pkgActivity, hotel, room")
	private Packages packages;
}
