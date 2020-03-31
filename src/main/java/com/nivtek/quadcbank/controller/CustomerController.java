/**
 * 
 */
package com.nivtek.quadcbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AsimSubedi
 *
 */
@Controller
public class CustomerController {
	
	@RequestMapping(value = {"/", "/login"})
	public String login() {
		
		return "login";
	}

}
