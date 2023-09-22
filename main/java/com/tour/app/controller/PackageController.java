package com.tour.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tour.app.dto.ActivitiesDto;
import com.tour.app.dto.ApiResponse;
import com.tour.app.dto.HotelDto;
import com.tour.app.dto.PackagesDto;
import com.tour.app.dto.PkgActivityDto;
import com.tour.app.pojo.FileResponse;
import com.tour.app.pojo.PkgActivity;
import com.tour.app.service.FileService;
import com.tour.app.service.IPackageService;

@RestController
@RequestMapping("/package")
@CrossOrigin("*")
public class PackageController {
	@Autowired
	private IPackageService pkgService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}/package")
	private String path;

	@PostMapping("/{packageId}/upload")
	public ResponseEntity<FileResponse> fileUpload(@Valid @PathVariable Integer packageId,
			@RequestParam("image") MultipartFile[] image) throws IOException {

		String fpath = path + "/" + packageId + "/";
		String updatedFullPath = "";
		for (MultipartFile file : image) {
			updatedFullPath = this.fileService.uploadImage(fpath, file);
		}
		FileResponse f = new FileResponse(updatedFullPath, "Uploaded");
		PackagesDto pdto = pkgService.setImgPath(packageId, updatedFullPath);
		System.out.println(pdto);
		return new ResponseEntity<FileResponse>(f, HttpStatus.OK);
	}

	// method to serve files.
	@GetMapping(value = "/serve/{hotelId}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, @PathVariable("hotelId") String hotelId,
			HttpServletResponse response) throws IOException {

//		File folder = new File(path+"/"+hotelId+"/"+"room");
//		File[] listOfFiles = folder.listFiles();

		InputStream resource = this.fileService.getResource(path, hotelId, imageName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	// Create Package.
	@PostMapping
	public ResponseEntity<PackagesDto> createPackage(@Valid @RequestBody PackagesDto pkgDto) {
		System.out.println("Inside create package");
		// Create Package.
		PackagesDto newpkgdto = this.pkgService.createPackage(pkgDto);
		return new ResponseEntity<PackagesDto>(newpkgdto, HttpStatus.CREATED);
	}

	// Update Package.
	@PutMapping
	public ResponseEntity<PackagesDto> updatePackage(@Valid @RequestBody PackagesDto pkgDto) {
		System.out.println("Inside update package");
		PackagesDto updatepkDto = this.pkgService.updatePackage(pkgDto);
		return new ResponseEntity<PackagesDto>(updatepkDto, HttpStatus.OK);
	}

	// Delete Package.
	@DeleteMapping("/{packageId}")
	public ResponseEntity<ApiResponse> deletePackage(@PathVariable Integer packageId) {
		System.out.println("Inside Delete pkg");
		this.pkgService.deletePackage(packageId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("pkg deleted successfully"), HttpStatus.OK);
	}

	// Get Package By PackageID.
	@GetMapping("/{packageId}")
	public ResponseEntity<PackagesDto> getPackageById(@Valid @PathVariable Integer packageId) {
		PackagesDto pkg = this.pkgService.getPackageById(packageId);
		return new ResponseEntity<PackagesDto>(pkg, HttpStatus.OK);
	}

	// Deactivate Package.
	@PutMapping("/{packageId}")
	public ResponseEntity<ApiResponse> deactivatePackage(@PathVariable Integer packageId) {
		System.out.println("Inside Deactivate pkg");
		this.pkgService.deactivatePackage(packageId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("pkg deactivated successfully"), HttpStatus.OK);
	}

	// Get All Packages.
	@GetMapping
	public ResponseEntity<List<PackagesDto>> getPackages() {
		return ResponseEntity.ok(this.pkgService.getAllPackages());
	}

	// Get Packages By Dest City.
	@GetMapping("/filterByCity/{destinationCity}")
	public ResponseEntity<List<PackagesDto>> getPackagesByCity(@PathVariable String destinationCity) {

		return ResponseEntity.ok(this.pkgService.getPackagesByCity(destinationCity));
	}

}
