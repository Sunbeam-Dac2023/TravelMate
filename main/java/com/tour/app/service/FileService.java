package com.tour.app.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	String uploadImage(String path,MultipartFile file);
	
	InputStream getResource(String path, String hotelId, String fileName) throws FileNotFoundException;

	InputStream getResource(String path, String hotelId, String roomId, String fileName) throws FileNotFoundException;
}
