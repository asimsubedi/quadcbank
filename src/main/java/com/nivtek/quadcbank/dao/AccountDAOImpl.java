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

	private final String QUERY_CUSTOMER_ACCOUNT = "SELECT * FROM quadcbank.account WHERE accountNumber=?";

	private final String INSERT_IN_TRANSACTION = "INSERT INTO quadcbank.transaction (amount, transactionType, remarks, transactionDate, accountId) VALUES (?,?,?,?,?)";

	private final String UPDATE_AMOUNT_IN_ACCOUNT = "UPDATE quadcbank.account SET balance=? WHERE accountId=?";

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

	/**
	 * This method inserts transaction Data for sender and receiver and updates the
	 * balance in account table for both sender and receiver as well.
	 */
	@Override
	public boolean transferFund(Transaction senderTransaction, Transaction receiverTransaction,
			Double senderUpdatedBalance, String receiversAccountNum) {

		Object[] senderParams = new Object[] { senderTransaction.getAmount(), senderTransaction.getTransactionType(),
				senderTransaction.getRemarks(), senderTransaction.getTransactionDate(),
				senderTransaction.getTransactionId() };

		int insertInTransaction = jdbcTemplate.update(INSERT_IN_TRANSACTION, senderParams); // This will update
																							// transaction Table for
																							// sender
		System.out.println("=1=INSERT In TRANSACTION: " + insertInTransaction);

		int updateSenderAccount = jdbcTemplate.update(UPDATE_AMOUNT_IN_ACCOUNT,
				new Object[] { senderUpdatedBalance, senderTransaction.getTransactionId() });// update sender's account
																								// balance
		System.out.println("=2== UPDATE AMOUNT FOR SENDER SUCCESS: " + updateSenderAccount);

		// Get Receiver's Account Details
		Account receiversAccount = jdbcTemplate.queryForObject(QUERY_CUSTOMER_ACCOUNT,
				new Object[] { receiversAccountNum }, (resultSet, rowNum) -> {
					Account accountFromDb = new Account();

					accountFromDb.setAccountId(resultSet.getInt("accountId"));
					accountFromDb.setAccountNumber(receiversAccountNum);
					accountFromDb.setBalance(resultSet.getDouble("balance"));
					accountFromDb.setAccountType(resultSet.getString("accountType"));

					return accountFromDb;

				});

		System.out.println("=3== RECEIVERS ACCOUNT BEFORE: " + receiversAccount);

		Object[] receiverParam = new Object[] { receiverTransaction.getAmount(),
				receiverTransaction.getTransactionType(), receiverTransaction.getRemarks(),
				receiverTransaction.getTransactionDate(), receiversAccount.getAccountId() };

		int insertInReceiverTransaction = jdbcTemplate.update(INSERT_IN_TRANSACTION, receiverParam);// update receiver's
																									// transaction table

		System.out.println("=4===Insert into Receiver Transaction: " + insertInReceiverTransaction);

		Double receiversNewBalance = receiversAccount.getBalance() + receiverTransaction.getAmount();

		int updateReceiversAccount = jdbcTemplate.update(UPDATE_AMOUNT_IN_ACCOUNT,
				new Object[] { receiversNewBalance, receiversAccount.getAccountId() });// update receiver's account
																						// balance

		System.out.println("=5===UpdateReceiversAccount: " + updateReceiversAccount);

		int totalDbSuccess = insertInTransaction + updateSenderAccount + insertInReceiverTransaction
				+ updateReceiversAccount;

		if (totalDbSuccess == 4)
			return true;

		else
			return false;
	}

}
