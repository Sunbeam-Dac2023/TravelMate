package com.tour.app.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PkgActivity")
public class PkgActivity {
	@Id
	@Column(name = "PkgActivityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pkgActivityId;

	@Column(name = "DayOfActivity")
	private int dayOfActivity;

	@ManyToOne
	@JoinColumn(name = "ActivityId")
	private Activities activities;
	
	@Column(name="IsActive", columnDefinition = "boolean default true")
	private boolean pkgActStatus;

	@ManyToOne
	@JoinColumn(name = "PackageId")
	private Packages packages;

	public PkgActivity(int dayOfActivity, boolean isPkgActStatus) {
		super();
		this.dayOfActivity = dayOfActivity;
		this.pkgActStatus = isPkgActStatus;
	}

}
