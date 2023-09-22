package com.tour.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tour.app.pojo.Payments;
import com.tour.app.pojo.Users;

@Repository
public interface PaymentsDao extends JpaRepository<Payments, Integer>{
	List<Payments> findByUser(Users user);
}
