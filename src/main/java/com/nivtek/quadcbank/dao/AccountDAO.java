/**
 * 
 */
package com.nivtek.quadcbank.dao;

import java.util.List;

import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Transaction;

/**
 * @author AsimSubedi
 *
 */
public interface AccountDAO {

	/**
	 * This methods will return the account object containing all account
	 * information for given customer.
	 * 
	 * @param customerId
	 * @return Account Object for given customer
	 */
	Account getCustomerAccount(int customerId);

	/**
	 * This method will return the list of transactions for given account Id of
	 * customer
	 * 
	 * @param accountId
	 * @return List of Transactions for given account
	 */
	List<Transaction> getAccountTransactions(Integer accountId);

	/**
	 * This method updates the transaction table for sender and receiver. and
	 * updates the account balance of sender and receiver as well.
	 * 
	 * @param senderTransaction
	 * @param receiverTransaction
	 * @param senderUpdatedBalance
	 * @param receiversAccountNum
	 * @return true if fund transfer success
	 */
	boolean transferFund(Transaction senderTransaction, Transaction receiverTransaction, Double senderUpdatedBalance,
			String receiversAccountNum);

}
