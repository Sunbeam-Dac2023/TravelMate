package com.tour.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Activities;
import com.tour.app.pojo.Packages;
import com.tour.app.pojo.PkgActivity;

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
public class ActivitiesDto {
	private int activityId;
	private String activityName;
	private String activityAddress;
	private String destinationCity;
	private String activityType;
	private double activityRate;
	private boolean activityActiveStatus;
}

