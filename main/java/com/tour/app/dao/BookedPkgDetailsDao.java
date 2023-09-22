package com.tour.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.BookedPkgDetails;
import com.tour.app.pojo.Flights;

@Repository
public interface BookedPkgDetailsDao extends JpaRepository<BookedPkgDetails, Integer>  {

}
