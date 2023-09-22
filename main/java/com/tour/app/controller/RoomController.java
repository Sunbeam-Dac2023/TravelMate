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

import com.tour.app.dto.ApiResponse;
import com.tour.app.dto.HotelDto;
import com.tour.app.dto.RoomsDto;
import com.tour.app.pojo.FileResponse;
import com.tour.app.service.FileService;
import com.tour.app.service.IRoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private IRoomService roomService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}/hotel")
	private String path;

	@PostMapping("/{hotelId}/{roomId}/upload")
	public ResponseEntity<FileResponse> fileUpload(@Valid @PathVariable Integer hotelId, @PathVariable Integer roomId,
			@RequestParam("image") MultipartFile[] image) throws IOException {

		String fpath = path + "/" + hotelId + "/room" + roomId + "/";
		for (MultipartFile file : image) {
			this.fileService.uploadImage(fpath, file);
		}
		FileResponse f = new FileResponse(fpath, "Uploaded");
		RoomsDto hdto = roomService.setImgPath(roomId, fpath);
		System.out.println(hdto);
		return new ResponseEntity<FileResponse>(f, HttpStatus.OK);
	}
	
	// method to serve files.
	@GetMapping(value = "/serve/{hotelId}/{roomId}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,@PathVariable("hotelId") String hotelId,
			@PathVariable("roomId") String roomId, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path,hotelId, roomId, imageName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	// Create Room.
	@PostMapping("/{hotelId}")
	public ResponseEntity<RoomsDto> createRoom(@Valid @RequestBody RoomsDto roomDto, @PathVariable Integer hotelId) {
		System.out.println("Inside add room");
		RoomsDto newlyCreatedRoom = this.roomService.createRoom(roomDto, hotelId);
		return new ResponseEntity<RoomsDto>(newlyCreatedRoom, HttpStatus.CREATED);
	}

	// Update Room.
	@PutMapping
	public ResponseEntity<RoomsDto> updateRoom(@Valid @RequestBody RoomsDto roomDto) {
		System.out.println("Inside Update room");
		RoomsDto updatedRoom = this.roomService.updateRoom(roomDto);
		return new ResponseEntity<RoomsDto>(updatedRoom, HttpStatus.OK);
	}

	// Delete Room.
	@DeleteMapping("/{roomId}")
	public ResponseEntity<ApiResponse> deleteRoom(@PathVariable Integer roomId) {
		System.out.println("Inside Delete room");
		this.roomService.deleteRoom(roomId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Room deleted successfully"), HttpStatus.OK);
	}

	// Deactivate Room.
	@PutMapping("/{roomId}")
	public ResponseEntity<ApiResponse> deactivateRoom(@PathVariable Integer roomId) {
		System.out.println("Inside Deactivate room");
		this.roomService.deactivateRoom(roomId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Room deactivated successfully"), HttpStatus.OK);
	}

	// Get room By Id.
	@GetMapping("/{roomId}")
	public ResponseEntity<RoomsDto> getRoomById(@Valid @PathVariable Integer roomId) {
		RoomsDto room = this.roomService.getRoomById(roomId);
		return new ResponseEntity<RoomsDto>(room, HttpStatus.OK);
	}

	// Get All rooms of Specific Hotel.
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<RoomsDto>> getAllRoomsByHotel(@PathVariable Integer hotelId) {
		return ResponseEntity.ok(this.roomService.getAllRoomsByHotel(hotelId));

	}
}
