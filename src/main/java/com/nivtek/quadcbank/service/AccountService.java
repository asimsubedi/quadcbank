/**
 * 
 */
package com.nivtek.quadcbank.service;

import java.util.List;

import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Customer;
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

	/**
	 * This method will return all the transactions for given account id.
	 * 
	 * @param accountId
	 * @return List of Transactions for given account Id
	 */
	List<Transaction> getAccountTransactions(Integer accountId);

	/**
	 * This method will transfer the fund from one account to requested account. If
	 * fund transfer success return true else return false.
	 * 
	 * @param sender
	 * @param receiversAccountNumber 
	 * @param transaction
	 * @return True if, fund Transfer Successful
	 */
	boolean transferFund(Customer sender, String receiversAccountNumber, Transaction transaction);

}
