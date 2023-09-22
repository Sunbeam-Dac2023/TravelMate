package com.tour.app.service;

import java.util.List;

import com.tour.app.dto.PaymentsDto;

public interface IPaymentService {
	
	PaymentsDto addPaymentDetails(PaymentsDto paymentsDto, Integer bookingId,Integer userId);

	List<PaymentsDto> getAllPaymentDetails();

	PaymentsDto getPaymentDetailsById(Integer paymentId);

	PaymentsDto updatePaymentStatus(Integer paymentId, String newPaymentStatus);	
}
