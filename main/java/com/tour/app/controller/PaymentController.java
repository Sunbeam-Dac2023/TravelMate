package com.tour.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tour.app.dto.PaymentsDto;
import com.tour.app.dto.UsersDto;
import com.tour.app.service.IPaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {
	@Autowired
	private IPaymentService paymentService;

//	// To Add Payment Details.
	@PostMapping
	public ResponseEntity<PaymentsDto> addPaymentDetails(@RequestBody PaymentsDto paymentsDto) {
		System.out.println("In Payment");
		PaymentsDto newlyCreatedPaymentDetails = this.paymentService.addPaymentDetails(paymentsDto,
				paymentsDto.getBookings().getBookingId(), paymentsDto.getUser().getUserId());
		return new ResponseEntity<>(newlyCreatedPaymentDetails, HttpStatus.OK);
	}

	// View All Payments.
	@GetMapping
	public ResponseEntity<List<PaymentsDto>> getAllPaymentDetails() {
		return ResponseEntity.ok(this.paymentService.getAllPaymentDetails());
	}

	// To View Particular Payment.
	@GetMapping("{paymentId}")
	public ResponseEntity<PaymentsDto> getPaymentDetailsById(@PathVariable Integer paymentId) {
		PaymentsDto payment = this.paymentService.getPaymentDetailsById(paymentId);
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	// To Update Particular User Details.
	@PutMapping("/{paymentId}")
	public ResponseEntity<PaymentsDto> updatePaymentStatus(@PathVariable("paymentId") Integer paymentId,
			@RequestParam(name = "newPaymentStatus") String newPaymentStatus) {
		System.out.println("Inside update Payment Status");
		PaymentsDto updatedPaymentTxn = this.paymentService.updatePaymentStatus(paymentId,newPaymentStatus);
		return new ResponseEntity<>(updatedPaymentTxn, HttpStatus.OK);
	}

}
