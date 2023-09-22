package com.tour.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.ActivitiesDao;
import com.tour.app.dao.PackageDao;
import com.tour.app.dao.PkgActDao;
import com.tour.app.pojo.Activities;
import com.tour.app.pojo.Packages;
import com.tour.app.pojo.PkgActivity;
import com.tour.app.service.IPkgActivityService;

@Service
@Transactional
public class PkgActivityServiceImpl implements IPkgActivityService {

	@Autowired
	private PkgActDao pkActDao;
	@Autowired
	private PackageDao pkgDao;
	
	@Autowired
	private ActivitiesDao actDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PkgActivity createPkgActivity(Integer PkgId, Integer ActId, Integer dayAct, boolean isPkgActStatus) {
		Packages pkgs = this.pkgDao.findById(PkgId).orElseThrow((() -> new ResourceNotFoundException("Pkg Not found")));
		Activities act = this.actDao.findById(ActId)
				.orElseThrow((() -> new ResourceNotFoundException("Act Not found")));
		PkgActivity newPkgAct = new PkgActivity(dayAct, isPkgActStatus);
		newPkgAct.setActivities(act);
		newPkgAct.setPackages(pkgs);
		PkgActivity pkAct = this.pkActDao.save(newPkgAct);
		return pkAct;
	}

	@Override
	public List<PkgActivity> updatePkgActivties(Integer PkgId, List<PkgActivity> newPkgActivityList) {
		List<PkgActivity> updatedPkgActiviy = new ArrayList<PkgActivity>();
		Packages pkgs = this.pkgDao.findById(PkgId).orElseThrow((() -> new ResourceNotFoundException("Pkg Not found")));

		newPkgActivityList.forEach(activity -> {
			
			if(activity.isPkgActStatus()) {
				activity.setPackages(pkgs);
				PkgActivity pkAct = this.pkActDao.save(activity);
				updatedPkgActiviy.add(pkAct);
			}else {
				this.pkActDao.delete(activity);
			}
		});
		return updatedPkgActiviy;
	}	

}
