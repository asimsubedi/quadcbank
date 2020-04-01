/**
 * 
 */
package com.nivtek.quadcbank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nivtek.quadcbank.entity.Address;
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

	private final String QUERY_ADDRESS = "SELECT * FROM quadcbank.address WHERE customerId=?";

	private final String UPDATE_ADDRESS = "update quadcbank.address SET line1=?, line2=?, city=?, state=?, zip=? WHERE customerId=?";

	/**
	 * This method will query for email and password, if found return the customer
	 * object else return null!!!
	 */
	@Override
	public Customer getCustomer(String email, String password) {
		Customer customer = new Customer();
		Object[] dbQueryParam = new Object[] { email, password };

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

		} catch (EmptyResultDataAccessException emptyData) {

			System.err.println("===== INSIDE CUSTOMERDAO: CUSTOMER NOT FOUND!! =====");
			customer = null;

		}

		return customer;
	}

	/**
	 * This method will query the database for ADDRESS QUERY and return the address
	 * object for customer.
	 */
	@Override
	public Address getCustomerAddress(int customerId) {

		Address address = new Address();
		Object[] queryParam = new Object[] { customerId };

		address = jdbcTemplate.queryForObject(QUERY_ADDRESS, queryParam, (resultSet, rowNum) -> {

			Address addressFromDB = new Address();

			addressFromDB.setLine1(resultSet.getString("line1"));
			addressFromDB.setLine2(resultSet.getString("line2"));
			addressFromDB.setCity(resultSet.getString("city"));
			addressFromDB.setState(resultSet.getString("state"));
			addressFromDB.setZip(resultSet.getString("zip"));

			return addressFromDB;

		});

		return address;
	}

	/**
	 * This method will update the customer's address and return the updated Address
	 */
	@Override
	public Address updateCustomerAddress(Address address, int customerId) {

		Object[] updateParams = new Object[] { address.getLine1(), address.getLine2(), address.getCity(),
				address.getState(), address.getZip(), customerId };

		int isUpdated = jdbcTemplate.update(UPDATE_ADDRESS, updateParams);
		
		if(isUpdated == 1)
			return address;
		
		else
			return null;
	}

}
