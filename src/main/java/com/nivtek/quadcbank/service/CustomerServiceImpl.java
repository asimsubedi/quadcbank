/**
 * 
 */
package com.nivtek.quadcbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivtek.quadcbank.dao.CustomerDAO;
import com.nivtek.quadcbank.entity.Address;
import com.nivtek.quadcbank.entity.Customer;

/**
 * @author AsimSubedi
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	/**
	 * This method requests the DAO layer to get Information if customer is logged
	 * in or not
	 */
	@Override
	public Customer checkCustomerLogin(String email, String password) {

		Customer loggedInCustomer = customerDAO.getCustomer(email, password);
		return loggedInCustomer;

	}

	/**
	 * This method will return the address of customer for given id.
	 */
	@Override
	public Address getCustomerAddress(int customerId) {

		Address customerAddress = customerDAO.getCustomerAddress(customerId);
		return customerAddress;

	}

	/**
	 * This method will update the address and returns the updated address object
	 */
	@Override
	public Address updateCustomerAddress(Address address, int customerId) {
		Address updatedAddress = customerDAO.updateCustomerAddress(address, customerId);
		return updatedAddress;
	}

}
