/**
 * 
 */
package com.nivtek.quadcbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivtek.quadcbank.dao.CustomerDAO;
import com.nivtek.quadcbank.entity.Customer;

/**
 * @author AsimSubedi
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Customer checkCustomerLogin(String email, String password) {

		Customer loggedInCustomer = customerDAO.getCustomer(email, password);
		return loggedInCustomer;

	}

}
