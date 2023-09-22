package com.tour.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.app.pojo.PkgActivity;

public interface PkgActDao extends JpaRepository<PkgActivity, Integer> {

}
