package com.tour.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.ActivitiesDto;
import com.tour.app.pojo.Activities;
import com.tour.app.service.IActivityService;

@RestController
@RequestMapping("/activity")
@CrossOrigin("*")
public class ActivityController {

	@Autowired
	private IActivityService activityService;

	// Create Activity.

	@PostMapping
	public ResponseEntity<ActivitiesDto> createActivity(@RequestBody ActivitiesDto activitiesDto) {
		ActivitiesDto newlyCreatedActivity = this.activityService.createActivity(activitiesDto);
		return new ResponseEntity<ActivitiesDto>(newlyCreatedActivity, HttpStatus.CREATED);
	}

	// Update Activity.
	@PutMapping
	public ResponseEntity<ActivitiesDto> updateActivity(@RequestBody ActivitiesDto activityDto) {
		ActivitiesDto updatedActivity = this.activityService.updateActivity(activityDto);
		return new ResponseEntity<ActivitiesDto>(updatedActivity, HttpStatus.OK);
	}

	// Get Activity By Id
	@GetMapping("/{activityId}")
	ResponseEntity<ActivitiesDto> getActivityById(@Valid @PathVariable Integer activityId) {
		ActivitiesDto activity = this.activityService.getActivityById(activityId);
		return new ResponseEntity<ActivitiesDto>(activity, HttpStatus.OK);

	}

	// Get All Activities.
	@GetMapping
	public ResponseEntity<List<ActivitiesDto>> getAllActivities() {
		return ResponseEntity.ok(this.activityService.getAllActivities());
	}
	
	@GetMapping("/getByDestcity")
	public ResponseEntity<List<ActivitiesDto>> getActivityByDestCity(@RequestParam(name = "cityName") String destinationCity) {
		return ResponseEntity.ok(this.activityService.getActivityByDestCity(destinationCity));
	}

}
