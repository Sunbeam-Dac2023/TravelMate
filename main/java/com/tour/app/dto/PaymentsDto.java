package com.tour.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class PaymentsDto {
	private int paymentId;
	private String modeOfPayment;
	private String debitAccountNumber;
	private String creditAccountNumber;
	private double totalAmount;
	private String paymentStatus;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime paymentDate;

	@JsonIgnoreProperties(value = "user")
	private Bookings bookings;
	
	@JsonIgnoreProperties(value = "bookings")
	private Users user;
}
