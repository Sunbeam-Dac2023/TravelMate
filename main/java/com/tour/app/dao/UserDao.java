package com.tour.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer>{
	Users findByEmail(String email);

}
