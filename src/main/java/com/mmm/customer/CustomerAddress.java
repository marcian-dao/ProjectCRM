
package com.mmm.customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "customer_address")
public class CustomerAddress {
	
	
	public CustomerAddress() {
		
		populateMapWithCountries();
		
	}
	
	public void populateMapWithCountries() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(new File("/json/result.json")));
			
			Map<String,String> countries = new Gson().fromJson(reader, new TypeToken<TreeMap<String,String>>(){}.getType());
			
			setCountries(countries);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Getter @Setter
	@Transient
	private Map<String,String> countries;

	@Getter @Setter
	@OneToOne(mappedBy="customerAddress", cascade = CascadeType.ALL)
	private CustomerDetails customerDetails;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Getter @Setter
	@NotNull(message = "this field is required")
	@Size(min = 1, message = "should be more than two letters")
	@Column(name = "house")
	private String house;
	
	@Getter @Setter
	@NotNull(message = "this field is required")
	@Size(min = 2, message = "should be more than two letters")
	@Column(name = "street")
	private String street;
	
	@Getter @Setter
	@NotNull(message = "this field is required")
	@Size(min = 2, message = "should be more than two letters")
	@Column(name = "city")
	private String city;

	@Getter @Setter
	@NotNull(message = "this field is required")
	@Size(min = 2, message = "should be more than two letters")
	@Column(name = "postCode")
	private String postCode;
	
	@Getter @Setter
	@NotNull(message = "this field is required")
	@Column(name = "country")
	private String country;
}
