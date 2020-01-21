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

@Component
@Entity
@Table(name = "customer_one")
public class CustomerDetails {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_address_id")
	private CustomerAddress customerAddress;

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}
	
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public CustomerDetails() {	

	}

	public CustomerDetails(CustomerAddress customerAddress,
			@NotNull(message = "this field is required") @Size(min = 2, max = 20) String firstName,
			@Size(min = 2, max = 20) @NotNull(message = "this field is required") String lastName,
			@Size(min = 2, max = 20) @NotNull(message = "this field is required") String email,
			@Size(min = 2, max = 20) @NotNull(message = "this field is required") String phone, Date registrationDate) {
		
		this.customerAddress = customerAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.registrationDate = registrationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
