package com.nivtek.quadcbank.dao;

import com.nivtek.quadcbank.entity.Customer;

/**
 * @author AsimSubedi
 *
 */
public interface CustomerDAO {

	/**
	 * This method returns the customer object for given email and password
	 * 
	 * @param email
	 * @param password
	 * @return Customer object
	 */
	Customer getCustomer(String email, String password);

}
