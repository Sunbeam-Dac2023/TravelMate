package com.tour.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.dao.BookedPkgDetailsDao;
import com.tour.app.pojo.BookedPkgDetails;
import com.tour.app.service.IBookedPkgDetails;

@Service
@Transactional
public class BookedPkgDetailsImpl implements IBookedPkgDetails {

	@Autowired
	BookedPkgDetailsDao bookedPkgDetailsDao;

	// Create Hotel
	@Override
	public BookedPkgDetails addBookedPkgdetails(BookedPkgDetails bookedPkgDetails) {
		BookedPkgDetails newBookedPkgDetails = this.bookedPkgDetailsDao.save(bookedPkgDetails);
		return newBookedPkgDetails;
	}

}
