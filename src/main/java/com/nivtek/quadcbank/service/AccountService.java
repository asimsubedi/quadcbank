/**
 * 
 */
package com.nivtek.quadcbank.service;

import java.util.List;

import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Transaction;

/**
 * @author AsimSubedi
 *
 */
public interface AccountService {

	/**
	 * This method will return the Account Object for given customer
	 * 
	 * @param currCustomerId
	 * @return Account Object for a customer
	 */
	Account getCustomerAccount(int customerId);

	List<Transaction> getAccountTransactions(Integer accountId);

}
