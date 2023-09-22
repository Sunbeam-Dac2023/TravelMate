package com.tour.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tour.app.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) {

		// File name
		String name = file.getOriginalFilename();
		// StringUtils.cleanPath(file.getOriginalFilename()

		// random name generate file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

		// Fullpath
		String filePath = path + fileName1;

		// create folder if not created
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}

		// file copy
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	@Override
	public InputStream getResource(String path, String hotelId, String fileName) throws FileNotFoundException {
		String fullPath = path + "/" + hotelId + "/" + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}
	
	@Override
	public InputStream getResource(String path, String hotelId, String roomId, String fileName) throws FileNotFoundException {
		String fullPath = path + "/" + hotelId + "/" + roomId + "/" + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
