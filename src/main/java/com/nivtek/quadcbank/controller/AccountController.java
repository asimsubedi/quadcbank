/**
 * 
 */
package com.nivtek.quadcbank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nivtek.quadcbank.entity.Account;
import com.nivtek.quadcbank.entity.Customer;
import com.nivtek.quadcbank.entity.Transaction;
import com.nivtek.quadcbank.service.AccountService;

/**
 * @author AsimSubedi
 *
 */
@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * This method will display the logged in customers basic account details like
	 * account number, current balance and account type.
	 * 
	 * @param session
	 * @return account-details view with Account Details
	 */
	@RequestMapping(value = "/user-dashboard/account-details")
	public String viewAccountDetails(HttpSession session) {

		if (session.getAttribute("customer") != null) {

			Customer currentCustomer = (Customer) session.getAttribute("customer");
			int currCustomerId = currentCustomer.getCustomerId();

			Account customerAccount = currentCustomer.getAccount();

			if (customerAccount == null)
				customerAccount = setCustomerAccount(currCustomerId);
			currentCustomer.setAccount(customerAccount);

			return "account-details";
		}

		else
			return "redirect:login";

	}

	/**
	 * This method will return the recent transactions of customer.
	 * 
	 * @param session
	 * @return Recent Transaction View Page
	 */
	@RequestMapping(value = "/user-dashboard/recent-transactions")
	public String viewRecentTransactions(HttpSession session) {

		if (session.getAttribute("customer") != null) {

			Customer currentCustomer = (Customer) session.getAttribute("customer");
			int currCustomerId = currentCustomer.getCustomerId();

			Account customerAccount = currentCustomer.getAccount();

			if (customerAccount == null) {
				customerAccount = setCustomerAccount(currCustomerId);
				currentCustomer.setAccount(customerAccount);

			}

			List<Transaction> accountTransactions = customerAccount.getTransactions();

			if (accountTransactions == null)
				accountTransactions = getCustomerTransactions(customerAccount.getAccountId());

			customerAccount.setTransactions(accountTransactions);

			return "account-transactions";

		}

		else
			return "redirect:login";

	}

	@GetMapping(value = "/user-dashboard/fund-transfer")
	public String showFundTransferForm(HttpSession session) {

		if (session.getAttribute("customer") != null) {

			return "fund-transfer-form";

		} else
			return "redirect:login";

	}

	@PostMapping(value = "/user-dashboard/fund-transfer-confirm")
	public String transferFund(@ModelAttribute("transaction") Transaction transaction, HttpServletRequest request,
			HttpSession session, Model model) {

		Customer currentCustomer = (Customer) session.getAttribute("customer");

		java.sql.Date transferDate = new java.sql.Date(new java.util.Date().getTime()); 
		final String accountNumber = request.getParameter("accountNumber");

		transaction.setTransactionDate(transferDate);

		boolean isTransferSuccess = accountService.transferFund(currentCustomer, accountNumber, transaction);

		if (isTransferSuccess) {
			model.addAttribute("transfer", "success");
			currentCustomer.setAccount(setCustomerAccount(currentCustomer.getCustomerId()));
			return "redirect:account-details";

		} else
			model.addAttribute("transfer", "failure");
			return "redirect:fund-transfer";

	}

	private List<Transaction> getCustomerTransactions(Integer accountId) {
		return accountService.getAccountTransactions(accountId);
	}

	private Account setCustomerAccount(int currCustomerId) {
		return accountService.getCustomerAccount(currCustomerId);
	}

}
