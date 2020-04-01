/**
 * 
 */
package com.nivtek.quadcbank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nivtek.quadcbank.entity.Customer;

/**
 * @author AsimSubedi
 *
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String QUERY_LOGIN = "SELECT * FROM quadcbank.customer WHERE email=? AND password=?";

	/**
	 * This method will query for email and password, if found return the customer
	 * object else return null!!!
	 */
	@Override
	public Customer getCustomer(String email, String password) {
		Customer customer = new Customer();
		Object[] dbQueryParam = new Object[] {email, password};
		
		try {
			
			customer = jdbcTemplate.queryForObject(QUERY_LOGIN, dbQueryParam, (resultSet, rowNum) -> {
				
				Customer customerfromDb = new Customer();
				
				customerfromDb.setCustomerId(resultSet.getInt("customerId"));
				customerfromDb.setFirstName(resultSet.getString("firstName"));
				customerfromDb.setLastName(resultSet.getString("lastName"));
				customerfromDb.setEmail(resultSet.getString("email"));
				customerfromDb.setRole(resultSet.getInt("role"));
				customerfromDb.setLoginStatus(resultSet.getInt("loginStatus"));
				
				return customerfromDb;
				
			});
			
		}catch(EmptyResultDataAccessException emptyData) {
			
			System.err.println("===== INSIDE CUSTOMERDAO: CUSTOMER NOT FOUND!! =====");
			customer = null;
			
		}

		return customer;
	}

}
