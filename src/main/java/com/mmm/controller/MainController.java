package com.mmm.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mmm.customer.CustomerAddress;
import com.mmm.customer.CustomerDetails;
import com.mmm.service.CustomerService;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String showHomePage() {	
		
		return "redirect:/loginPage";
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder databinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	private CustomerService customerService;

	@GetMapping("/showLogin")
	public String showLoginPage() {

		return "login.html";
	}

	@RequestMapping("/formToPocess")
	public String showFormToProcess(Model modelCust, Model modelAddress) {

		modelCust.addAttribute("customer", new CustomerDetails());

		modelAddress.addAttribute("customerAddress", new CustomerAddress());

		return "formToPocess.html";
	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") CustomerDetails customerEntity,
			BindingResult theBindingResultCustomer,
			@Valid @ModelAttribute("customerAddress") CustomerAddress customerAddressEntity,
			BindingResult theBindingResultAddress) {

		if ((theBindingResultCustomer.hasErrors()) | (theBindingResultAddress.hasErrors())) {

			return "formToPocess.html";

		} else {	
			
			customerEntity.setCustomerAddress(customerAddressEntity);

			Date date = new Date(System.currentTimeMillis());

			customerEntity.setRegistrationDate(date);

			customerService.addCustomer(customerEntity);
		}
		return "redirect:/showAllCustomers";
	}

	@GetMapping("/showCustomerAddress")
	public String displayCustomerAddress(@RequestParam("customerId") int theId, Model customerModel, Model addressModel) {
	
		CustomerDetails theCustomreName = customerService.getCustomer(theId);
		CustomerAddress theCustomerAddress = customerService.getCustomerAddress(theId);
		
		customerModel.addAttribute("customer", theCustomreName);
		addressModel.addAttribute("cusomerAddress", theCustomerAddress);
		
		return "displayCustAddress.html";
	}

	@GetMapping("/update")
	public String updateForm(@RequestParam("customerId") int theId, Model theCustModel, Model theAddressModel) {

		CustomerDetails theCustomerDetails = customerService.getCustomer(theId);
		
		CustomerAddress theCustomerAddress = theCustomerDetails.getCustomerAddress();

		theCustModel.addAttribute("customer", theCustomerDetails);

		theAddressModel.addAttribute("customerAddress", theCustomerAddress);

		return "formToPocess.html";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {

		customerService.deleteCustomer(theId);

		return "redirect:/showAllCustomers";
	}

	@RequestMapping("/showAllCustomers")
	public String showCustomersList(Model model) {

		List<CustomerDetails> theCustomers = customerService.getCustomers();

		model.addAttribute("customersListModel", theCustomers);

		return "displayAllCustomers.jsp";
	}

	@GetMapping("/search")
	public String searchForTheCustomer(@RequestParam("theSearchName") String name, Model theModel) {

		List<CustomerDetails> theCustomers = customerService.getCustomerbyName(name);

		theModel.addAttribute("customersListModel", theCustomers);

		return "displayAllCustomers.jsp";
	}
}