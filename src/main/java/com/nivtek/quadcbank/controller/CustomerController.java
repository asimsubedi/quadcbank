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
 * @author AsimSubedi
 *
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * This is login page. Always redirect users to login page at beginning
	 * 
	 * @return login page
	 */
	@RequestMapping(value = { "/", "/login" })
	public String login() {

		return "login";

	}

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
	
	@RequestMapping(value="/user-dashboard")
	public String userDashboard(HttpSession session) {
		
		if(session.getAttribute("customer") != null)
			return "user-dashboard";
		
		else
			return "redirect:login";
		
	}
	
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        session.invalidate();
        session = request.getSession(true);
        return "redirect:login?action=logout";
        
    }

}
