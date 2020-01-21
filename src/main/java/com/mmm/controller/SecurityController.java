package com.mmm.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.mmm.service.UserService;
import com.mmm.userEntity.User;
import com.mmm.usercrm.CRMuser;

@Controller
public class SecurityController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	private UserService userService;

	@GetMapping("/loginPage")
	public String showLoginPage() {

		return "login.html";
	}

	@GetMapping("/showRegistrationForm")

	public ModelAndView registerTheUser() {

		return new ModelAndView("register.html", "crmUser", new CRMuser());
	}

	@RequestMapping("/registerTheUser")
	public String registerNewUser(@Valid @ModelAttribute("crmUser") CRMuser crmUser, BindingResult theBindingResult,
			Model theModelnewUser, Model theModelError) {

		if (theBindingResult.hasErrors()) {

			return "register.html";

		} else {

			String userName = crmUser.getUserName();

			User existing = userService.findByUserName(userName);

			if (existing != null) {

				theModelnewUser.addAttribute("crmUser", new CRMuser());
				theModelError.addAttribute("registrationError", "This user already exists.");

				return "register.html";
			}

			userService.addNewUser(crmUser);

			return "registration-confirmation.html";
		}
	}

	@GetMapping("/accessDenied")
	public String showAccesDenied() {

		return "noEntry.html";
	}

	@GetMapping("/landingPage")
	public String showLandingPage() {

		return "landing.html";
	}

	@GetMapping("/showUsers")
	public String showExistingUser(Model model) {

		List<User> allUsers = userService.showUsers();

		model.addAttribute("allUsersModel", allUsers);

		return "displayAllUsers.jsp";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int userID) {

		userService.deleteUser(userID);

		return "redirect:/showUsers";
	}
}