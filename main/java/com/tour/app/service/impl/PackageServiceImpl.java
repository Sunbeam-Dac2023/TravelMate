package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.PackageDao;
import com.tour.app.dto.PackagesDto;
import com.tour.app.pojo.Packages;
import com.tour.app.service.IPackageService;

@Service
@Transactional
public class PackageServiceImpl implements IPackageService {
	@Autowired
	private PackageDao pkgDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PackagesDto setImgPath(Integer packageId, String fpath) {
		// TODO Auto-generated method stub
		Packages pkg = this.pkgDao.findById(packageId)
				.orElseThrow((() -> new ResourceNotFoundException("Pkg Not found")));
		pkg.setImgPath(fpath);
		Packages updatedpkg = this.pkgDao.save(pkg);
		return this.modelMapper.map(updatedpkg, PackagesDto.class);
	}
	
	@Override
	public PackagesDto deactivatePackage(Integer packageId) {
		Packages newpkg = this.pkgDao.findById(packageId)
				.orElseThrow((() -> new ResourceNotFoundException("Package Not found")));
		newpkg.setPackageActiveStatus(false);
		Packages uppkg = this.pkgDao.save(newpkg);
		return this.modelMapper.map(uppkg, PackagesDto.class);
	}

	@Override
	public PackagesDto createPackage(PackagesDto pkgDto) {
		Packages pkg = this.modelMapper.map(pkgDto, Packages.class);
		pkgDto.setPackageActiveStatus(true);
		pkgDto.getPkgActivity().forEach(activity -> {
			activity.setPackages(pkg);
		});
		Packages newPkg = this.pkgDao.save(pkg);
		return this.modelMapper.map(newPkg, PackagesDto.class);
	}

	@Override
	public PackagesDto updatePackage(PackagesDto pkgDto) {
		Packages newpkg = this.pkgDao.findById(pkgDto.getPackageId())
				.orElseThrow((() -> new ResourceNotFoundException("Package Not found")));
		newpkg.setDestinationCity(pkgDto.getDestinationCity());
		newpkg.setHotel(pkgDto.getHotel());
		newpkg.setNoOfNights(pkgDto.getNoOfNights());
		newpkg.setPackageBaseRate(pkgDto.getPackageBaseRate());
		newpkg.setPkgName(pkgDto.getPkgName());
		newpkg.setPkgType(pkgDto.getPkgType());
		newpkg.setRoom(pkgDto.getRoom());
		newpkg.setDropStatus(pkgDto.isDropStatus());
		newpkg.setFlightStatus(pkgDto.isFlightStatus());
		newpkg.setPackageActiveStatus(pkgDto.isPackageActiveStatus());

		// TODO
		// To update pkgActivity list
//		List<PkgActivity> updatedPkgActiviy = pkgActServ.updatePkgActivties(pkgDto.getPackageId(),pkgDto.getPkgActivity()); 
//		newpkg.setPkgActivity(updatedPkgActiviy);		// TODO:: Not Completed

		Packages updatepkg = this.pkgDao.save(newpkg);
		return this.modelMapper.map(updatepkg, PackagesDto.class);
	}

	@Override
	public void deletePackage(Integer packageId) {
		Packages packageToBeDeleted = this.pkgDao.findById(packageId)
				.orElseThrow((() -> new ResourceNotFoundException("Package Not found")));
		this.pkgDao.delete(packageToBeDeleted);
	}

	@Override
	public List<PackagesDto> getAllPackages() {
		List<Packages> allpkg = this.pkgDao.findAll();

		List<PackagesDto> aPkg = allpkg.stream().map(pk -> this.modelMapper.map(pk, PackagesDto.class))
				.collect(Collectors.toList());
		return aPkg;
	}

	@Override
	public PackagesDto getPackageById(Integer packageId) {
		Packages pkg = this.pkgDao.findById(packageId)
				.orElseThrow((() -> new ResourceNotFoundException("Package Not found")));
		return this.modelMapper.map(pkg, PackagesDto.class);
	}

	@Override
	public List<PackagesDto> getPackagesByCity(String destinationCity) {
		List<Packages> packages = this.pkgDao.findByDestinationCity(destinationCity);
		List<PackagesDto> allPackages = packages.stream().map(pkg -> this.modelMapper.map(pkg, PackagesDto.class))
				.collect(Collectors.toList());
		return allPackages;

	}

}
