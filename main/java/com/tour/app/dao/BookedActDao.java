package com.tour.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.app.pojo.BookedActivities;

public interface BookedActDao extends JpaRepository<BookedActivities, Integer> {

}
