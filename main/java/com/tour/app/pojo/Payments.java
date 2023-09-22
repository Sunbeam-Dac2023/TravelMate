package com.tour.app.pojo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Payments")
public class Payments {
	
	@Id
	@Column(name = "PaymentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	

	@Column(name="ModeOfPayment", length = 50)
	private String modeOfPayment;

	@Column(name="DebitAccountNumber", length = 20)
	private String debitAccountNumber;

	@Column(name="CreditAccountNumber", length = 20)
	private String creditAccountNumber;

	@Column(name="TotalAmount")
	private double totalAmount;

	@Column(name="PaymentStatus")
	private String paymentStatus;

	@Column(name="PaymentDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime paymentDate;
	
	// Reference
	@OneToOne()
	@JsonIgnore
	@JoinColumn(name = "BookingId", nullable = true) // Foreign key
	private Bookings bookings;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "UserId", nullable = true) // Foreign key
	private Users user;

	public Payments(int paymentId, String modeOfPayment, String debitAccountNumber, String creditAccountNumber,
			double totalAmount, String paymentStatus, LocalDateTime paymentDate) {
		super();
		this.paymentId = paymentId;
		this.modeOfPayment = modeOfPayment;
		this.debitAccountNumber = debitAccountNumber;
		this.creditAccountNumber = creditAccountNumber;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}
	
}
