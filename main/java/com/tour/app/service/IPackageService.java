package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.PackagesDto;

public interface IPackageService {

	PackagesDto createPackage(PackagesDto pkgDto);

	PackagesDto updatePackage(PackagesDto pkgDto);

	void deletePackage(Integer packageId);

	List<PackagesDto> getAllPackages();

	PackagesDto getPackageById(Integer packageId);

	PackagesDto deactivatePackage(Integer packageId);

	List<PackagesDto> getPackagesByCity(String destinationCity);
	
	PackagesDto setImgPath(Integer packageId,String fpath);


}
