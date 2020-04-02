/**
 * 
 */
package com.nivtek.quadcbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nivtek.quadcbank.dao.AccountDAO;
import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Customer;
import com.nivtek.quadcbank.entity.Transaction;

/**
 * @author AsimSubedi
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	/**
	 * GetCustomerAccount Implementation.
	 */
	@Override
	public Account getCustomerAccount(int customerId) {

		Account customerAccount = accountDAO.getCustomerAccount(customerId);

		return customerAccount;
	}

	/**
	 * Get Account Transactions Implementations
	 */
	@Override
	public List<Transaction> getAccountTransactions(Integer accountId) {
		List<Transaction> transactionsList = accountDAO.getAccountTransactions(accountId);
		return transactionsList;
	}

	/**
	 * Implementation of Fund Transfer behavior
	 */
	@Override
	@Transactional
	public boolean transferFund(Customer sender, String receiversAccountNum, Transaction transaction) {

		Transaction senderTransaction = new Transaction();
		Double senderBalance = sender.getAccount().getBalance();

		Double senderUpdatedBalance;

		Transaction receiverTransaction = new Transaction(transaction);
		Double fundInitialized = transaction.getAmount();

		// first check if requested account number exists!
		// used logic is Account Number Starts with CH100 for QuadCBAnk :)

		boolean accountExist = isAccountExists(receiversAccountNum);

		// if Account Number Exists, get his account Number and then,
		// make two transactions object to insert into transaction table.

		if (accountExist) {

			// Check if sender has enough balance

			if (senderBalance < fundInitialized) {
				System.out.println("=== Not Enough Balance!! ===");
				return false;

			} else {

				senderTransaction.setAmount(fundInitialized);
				senderTransaction.setTransactionType("Transfer");
				senderTransaction.setRemarks(transaction.getRemarks());
				senderTransaction.setTransactionDate(transaction.getTransactionDate());
				senderTransaction.setTransactionId(sender.getAccount().getAccountId()); // this transaction Id is senders accountId.

				senderUpdatedBalance = senderBalance - fundInitialized;

				receiverTransaction.setTransactionType("Deposit");
				receiverTransaction.setRemarks("By " + sender.getFirstName() + " " + sender.getLastName());
				receiverTransaction.setTransactionId(sender.getCustomerId());

				boolean isTransferSuccess = accountDAO.transferFund(senderTransaction, receiverTransaction,
						senderUpdatedBalance, receiversAccountNum);
				
				if(isTransferSuccess)
					return true;
				else
					return false;

			}

		}

		else
			return false;

	}

	/**
	 * This method will check if receiver's account belongs to QuadCBank or not.
	 * here, the logic is The QuadCBank Account Number Begins with CH100 :)
	 * 
	 * @param receiversAccountNum
	 * @return true or false based on account exists or not
	 */
	private boolean isAccountExists(String receiversAccountNum) {

		if (receiversAccountNum.startsWith("CH100"))
			return true;

		else
			return false;

	}

}
