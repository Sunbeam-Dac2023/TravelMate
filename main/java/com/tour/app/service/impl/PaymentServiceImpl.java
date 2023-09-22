package com.tour.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.app.customexceptions.ResourceNotFoundException;
import com.tour.app.dao.BookingDao;
import com.tour.app.dao.PaymentsDao;
import com.tour.app.dao.UserDao;
import com.tour.app.dto.PaymentsDto;
import com.tour.app.pojo.Bookings;
import com.tour.app.pojo.Payments;
import com.tour.app.pojo.Users;
import com.tour.app.service.IPaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private PaymentsDao paymentsDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	// To Add Payment Details
	@Override
	public PaymentsDto addPaymentDetails(PaymentsDto paymentDto, Integer bookingId, Integer userDao) {
		Bookings bookings = this.bookingDao.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking Not found")));
		
		Users newUser = this.userDao.findById(userDao)
				.orElseThrow((() -> new ResourceNotFoundException("User Not Found!")));
		
		Payments payment = this.modelMapper.map(paymentDto, Payments.class);
		payment.setBookings(bookings);
		bookings.setPayment(payment);
		payment.setUser(newUser);
		payment.setCreditAccountNumber("57789645888");	// TODO  : Credit account number must be take from proeprty file
		Payments newCreatedPayment = this.paymentsDao.save(payment);
		
		
		//
		
		return this.modelMapper.map(newCreatedPayment, PaymentsDto.class);
	}

	// View All Payments
	@Override
	public List<PaymentsDto> getAllPaymentDetails() {
		List<Payments> payments = this.paymentsDao.findAll();
		List<PaymentsDto> allPayments = payments.stream()
				.map(payment -> this.modelMapper.map(payment, PaymentsDto.class)).collect(Collectors.toList());
		return allPayments;
	}

	// To View Particular Payment
	@Override
	public PaymentsDto getPaymentDetailsById(Integer paymentId) {
		Payments payment = this.paymentsDao.findById(paymentId)
				.orElseThrow((() -> new ResourceNotFoundException("Payment Id is Invalid")));
		return this.modelMapper.map(payment, PaymentsDto.class);
	}

	@Override
	public PaymentsDto updatePaymentStatus(Integer paymentId, String newPaymentStatus) {
		Payments payment = this.paymentsDao.findById(paymentId)
				.orElseThrow((() -> new ResourceNotFoundException("Payment Id is Invalid")));
		payment.setPaymentStatus(newPaymentStatus);
		Payments updatedPayment = this.paymentsDao.save(payment);
		return this.modelMapper.map(updatedPayment, PaymentsDto.class);
	}
}
