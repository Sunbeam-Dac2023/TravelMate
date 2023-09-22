package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.ActivitiesDto;
import com.tour.app.pojo.Activities;

public interface IActivityService {
	ActivitiesDto createActivity(ActivitiesDto activityDto); // Add Activity

	ActivitiesDto updateActivity(ActivitiesDto activityDto); // Update Activity

	List<ActivitiesDto> getAllActivities(); // get All Activities

	ActivitiesDto getActivityById(Integer activityId); // get Activity By Id

	List<ActivitiesDto> getActivityByDestCity(String destinationCity); // getActivityByCityName
}
