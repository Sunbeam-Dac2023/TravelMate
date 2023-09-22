package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.ActivitiesDao;
import com.tour.app.dto.ActivitiesDto;
import com.tour.app.pojo.Activities;
import com.tour.app.service.IActivityService;

@Service
@Transactional
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ActivitiesDao activitiesDao;

	// Create Activity
	@Override
	public ActivitiesDto createActivity(ActivitiesDto activityDto) {
		Activities activity = this.modelMapper.map(activityDto, Activities.class);
		Activities newlyCreatedActivity = this.activitiesDao.save(activity);
		return this.modelMapper.map(newlyCreatedActivity, ActivitiesDto.class);
	}

	// Update Activity
	@Override
	public ActivitiesDto updateActivity(ActivitiesDto updatedActivityDto) {
		Activities newActivity = this.activitiesDao.findById(updatedActivityDto.getActivityId())
				.orElseThrow((() -> new ResourceNotFoundException("Activity Not found")));

		newActivity.setActivityName(updatedActivityDto.getActivityName());
		newActivity.setActivityAddress(updatedActivityDto.getActivityAddress());
		newActivity.setDestinationCity(updatedActivityDto.getDestinationCity());
		newActivity.setActivityType(updatedActivityDto.getActivityType());
		newActivity.setActivityRate(updatedActivityDto.getActivityRate());
		newActivity.setActivityActiveStatus(updatedActivityDto.isActivityActiveStatus());
//		newActivity.setPkgActivity(updatedActivityDto.getPkgActivity());

		Activities updatedActivity = this.activitiesDao.save(newActivity);

		return this.modelMapper.map(updatedActivity, ActivitiesDto.class);
	}

	// Get all activities
	@Override
	public List<ActivitiesDto> getAllActivities() {
		List<Activities> activities = this.activitiesDao.findAll();
		List<ActivitiesDto> allActivities = activities.stream()
				.map(activity -> this.modelMapper.map(activity, ActivitiesDto.class)).collect(Collectors.toList());
		return allActivities;
	}

	// Get Activity By Id
	@Override
	public ActivitiesDto getActivityById(Integer activityId) {
		Activities activity = this.activitiesDao.findById(activityId)
				.orElseThrow((() -> new ResourceNotFoundException("Activity Not Found")));
		return this.modelMapper.map(activity, ActivitiesDto.class);
	}

	//Get all activity List By Destination City
	@Override
	public List<ActivitiesDto> getActivityByDestCity(String destinationCity) {
		List<Activities> activities = this.activitiesDao.findByDestinationCity(destinationCity);
		List<ActivitiesDto> allActivities = activities.stream()
				.map(activity -> this.modelMapper.map(activity, ActivitiesDto.class)).collect(Collectors.toList());
		return allActivities;		
	}

}
