/**
 * 
 */
package com.nivtek.quadcbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Transaction;

/**
 * @author AsimSubedi
 *
 */
@Repository
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String QUERY_ACCOUNT = "SELECT * FROM quadcbank.account WHERE customerId=?";

	private final String QUERY_TRANSACTIONS = "SELECT * FROM quadcbank.transaction WHERE accountId = ? ORDER BY transactionDate DESC";

	/**
	 * This method is returning the Account Object for Requested Customer using his
	 * CustomerId
	 */
	@Override
	public Account getCustomerAccount(int customerId) {

		Account account = new Account();

		account = jdbcTemplate.queryForObject(QUERY_ACCOUNT, new Object[] { customerId }, (resultSet, rowNum) -> {

			Account accountFromDB = new Account();

			accountFromDB.setAccountId(resultSet.getInt("accountId"));
			accountFromDB.setAccountNumber(resultSet.getString("accountNumber"));
			accountFromDB.setBalance(resultSet.getDouble("balance"));
			accountFromDB.setAccountType(resultSet.getString("accountType"));

			return accountFromDB;

		});

		return account;
	}

	/**
	 * This method is returning List of Transactions for given accountNumber
	 */
	@Override
	public List<Transaction> getAccountTransactions(Integer accountId) {

		List<Transaction> transactions = jdbcTemplate.query(QUERY_TRANSACTIONS, new Object[] { accountId },
				(resultSet, rowNum) -> {

					Transaction transaction = new Transaction();
					
					transaction.setTransactionId(resultSet.getInt("transactionId"));
					transaction.setAmount(resultSet.getDouble("amount"));
					transaction.setTransactionType(resultSet.getString("transactionType"));
					transaction.setRemarks(resultSet.getString("remarks"));
					transaction.setTransactionDate(resultSet.getDate("transactionDate"));
					
					return transaction;

				});

		return transactions;

	}

}
