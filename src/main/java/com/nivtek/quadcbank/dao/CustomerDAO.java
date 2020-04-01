package com.nivtek.quadcbank.dao;

import com.nivtek.quadcbank.entity.Address;
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

	/**
	 * This method will query in DB to get the address of given customer for his
	 * customerId.
	 * 
	 * @param customerId
	 * @return Address object for given customerId
	 */
	Address getCustomerAddress(int customerId);

	/**
	 * This method will update the customer's address in DB and return the new
	 * address of customer.
	 * 
	 * @param address
	 * @param customerId 
	 * @return updated Address Object for customer.
	 */
	Address updateCustomerAddress(Address address, int customerId);

}
