package com.mmm.customer;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Entity
@Table(name = "customer_one")
public class CustomerDetails {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_address_id")
	private CustomerAddress customerAddress;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "this field is required")
	@Size(min = 2, max = 15)
	@Column(name = "first_name")
	private String firstName;

	@Size(min = 2, max = 15)
	@NotNull(message = "this field is required")
	@Column(name = "last_name")
	private String lastName;

	@Size(min = 2, max = 20)
	@NotNull(message = "this field is required")
	@Column(name = "email")
	private String email;

	@Size(min = 2, max = 15)
	@NotNull(message = "this field is required")
	@Column(name = "phone")
	private String phone;

	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
}
