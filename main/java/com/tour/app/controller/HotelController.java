package com.tour.app.controller;

import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tour.app.dto.HotelDto;
import com.tour.app.pojo.FileResponse;
import com.tour.app.service.FileService;
import com.tour.app.service.IHotelService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin("*")
public class HotelController {

	@Autowired
	private IHotelService hotelService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}/hotel")
	private String path;

	@PostMapping("/{hotelId}/upload")
	public ResponseEntity<FileResponse> fileUpload(@Valid @PathVariable Integer hotelId,
			@RequestParam("image") MultipartFile[] image) throws IOException {

		String fpath = path + "/" + hotelId + "/";
		String updatedFullPath = "";
		for (MultipartFile file : image) {
			updatedFullPath = this.fileService.uploadImage(fpath, file);
		}
		FileResponse f = new FileResponse(updatedFullPath, "Image Uploaded");
		HotelDto hdto = hotelService.setImgPath(hotelId, updatedFullPath);
		System.out.println(hdto);
		return new ResponseEntity<FileResponse>(f, HttpStatus.OK);
	}

	// method to serve files.
	@GetMapping(value = "/serve/{hotelId}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,@PathVariable("hotelId") String hotelId, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path,hotelId, imageName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	// Create Hotel.
	@PostMapping
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {
		HotelDto newlyCreatedHotel = this.hotelService.createHotel(hotelDto);
		return new ResponseEntity<HotelDto>(newlyCreatedHotel, HttpStatus.CREATED);
	}

	// Update Hotel.
	@PutMapping
	public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotelDto) {
		HotelDto updatedHotel = this.hotelService.updateHotel(hotelDto);
		return new ResponseEntity<HotelDto>(updatedHotel, HttpStatus.OK);
	}

	// Deactivate Hotel.
	@PutMapping("/deactivate/{hotelId}")
	public ResponseEntity<HotelDto> deactivateHotel(@Valid @PathVariable Integer hotelId) {
		HotelDto updatedHotel = this.hotelService.deactivateHotel(hotelId);
		return new ResponseEntity<HotelDto>(updatedHotel, HttpStatus.OK);
	}

	// Get Hotel By ID with Room details.
	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelDto> getHotelById(@Valid @PathVariable Integer hotelId) {
		HotelDto hotel = this.hotelService.getHotelById(hotelId);
		return new ResponseEntity<HotelDto>(hotel, HttpStatus.OK);
	}

	// Get All hotels with Rooms.
	@GetMapping
	public ResponseEntity<List<HotelDto>> getAllHotels() {
		return ResponseEntity.ok(this.hotelService.getAllHotels());
	}

	// Get Hotel By ID with Room details.
	@GetMapping("/getByDestcity")
	public ResponseEntity<List<HotelDto>> getHotelByDestinationCity(
			@RequestParam(name = "cityName") String destinationCity) {
		return ResponseEntity.ok(this.hotelService.getHotelByDestinationCity(destinationCity));
	}

}