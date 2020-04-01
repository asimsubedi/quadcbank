/**
 * 
 */
package com.nivtek.quadcbank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nivtek.quadcbank.entity.Customer;
import com.nivtek.quadcbank.service.CustomerService;

/**
 * CustomerController class basically responsible to handle the login and
 * displaying dashboard if loggedin or redirect to login if unable to verify.
 * 
 * @author AsimSubedi
 *
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * This method displays the Login Page.
	 * 
	 * @return login page
	 */
	@RequestMapping(value = { "/", "/login" })
	public String displayLoginPage() {

		return "login";

	}

	/**
	 * This method handles the user login credentials. If user is present, show him
	 * dashboard, else redirect to login page.
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return user-dashboard page if login successful
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleCustomerLogin(HttpServletRequest request, Model model, HttpSession session) {

		String viewName = "";

		Customer loggedInCustomer = customerService.checkCustomerLogin(request.getParameter("email"),
				request.getParameter("password"));

		if (loggedInCustomer != null) {
			session.setAttribute("customer", loggedInCustomer);
			viewName = "redirect:user-dashboard";

		} else {

			model.addAttribute("errorMsg", "Sorry! Couldn't Login! Try Again!!");
			viewName = "login";
		}

		return viewName;

	}

	/**
	 * This method is responsible to show the user dashboard view. Only the loggedIn
	 * user can view this page.
	 * 
	 * @param session
	 * @return user-dashboard view
	 */
	@RequestMapping(value = "/user-dashboard")
	public String showUserDashboard(HttpSession session) {

		if (session.getAttribute("customer") != null)
			return "user-dashboard";

		else
			return "redirect:login";

	}

	/**
	 * This method is responsible to logout the user by invalidating the sessions.
	 * After logout user is redirected to login page.
	 * 
	 * @param request
	 * @param session
	 * @return login-page view
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		session = request.getSession(true);
		return "redirect:login?action=logout";

	}

}
