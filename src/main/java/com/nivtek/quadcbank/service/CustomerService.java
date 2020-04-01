/**
 * 
 */
package com.nivtek.quadcbank.service;

import com.nivtek.quadcbank.entity.Customer;

/**
 * @author AsimSubedi
 *
 */
public interface CustomerService {

	/**
	 * This method handles the login operation using the credentials. If user is
	 * present, return customer object, else return null
	 * 
	 * @param email    of customer
	 * @param password of customer
	 * @return loggedInCustomer
	 */
	Customer checkCustomerLogin(String email, String password);

}
