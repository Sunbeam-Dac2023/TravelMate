package com.tour.app.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Activities")
public class Activities {
	@Id
	@Column(name = "activity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int activityId;

	@Column(name = "activity_name", length = 100)
	private String activityName;

	@Column(name = "activity_address", length = 100)
	private String activityAddress;

	@Column(name = "destinationcity", length = 20)
	private String destinationCity;

	@Column(name = "activity_type", length = 30)
	private String activityType;

	@Column(name = "activity_rate")
	private double activityRate;
	// private byte[] activityImgs;

	@Column(name = "isactivityactive", columnDefinition = "boolean default false")
	private boolean activityActiveStatus;

	public Activities(String activityName, String activityAddress, String destinationCity, String activityType,
			double activityRate, boolean activityActiveStatus) {
		super();
		this.activityName = activityName;
		this.activityAddress = activityAddress;
		this.destinationCity = destinationCity;
		this.activityType = activityType;
		this.activityRate = activityRate;
		this.activityActiveStatus = activityActiveStatus;
	}
}
