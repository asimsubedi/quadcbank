/**
 * 
 */
package com.nivtek.quadcbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivtek.quadcbank.dao.AccountDAO;
import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Transaction;

/**
 * @author AsimSubedi
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public Account getCustomerAccount(int customerId) {

		Account customerAccount = accountDAO.getCustomerAccount(customerId);

		return customerAccount;
	}

	@Override
	public List<Transaction> getAccountTransactions(Integer accountId) {
		List<Transaction> transactionsList = accountDAO.getAccountTransactions(accountId);
		return transactionsList;
	}

}
