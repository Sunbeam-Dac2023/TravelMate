package com.tour.app.service;

import java.util.List;

import com.tour.app.pojo.PkgActivity;

public interface IPkgActivityService {
	PkgActivity createPkgActivity(Integer PkgId, Integer ActId, Integer dayAct, boolean isPkgActStatus);
	
	List<PkgActivity> updatePkgActivties(Integer PkgId, List<PkgActivity> newPkgActivityList);
	
}
