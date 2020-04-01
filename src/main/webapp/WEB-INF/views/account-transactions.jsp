<%@include file="common/header.jsp"%>
<%@include file="common/nav.jsp"%>

<!-- BODY STARTS HERE -->
<div class="container" style="margin-top: 40px">
	<div class="row">

		<div class="col-sm-4 col-lg-3">
			<div class="list-group list-group-flush">
				<a href="${request.contextPath}/quadcbank/user-dashboard"
					class="list-group-item list-group-item-action">Dashboard</a> <a
					href="${request.contextPath}/quadcbank/user-dashboard/recent-transactions"
					class="list-group-item list-group-item-action">Recent
					Transactions</a> <a
					href="${request.contextPath}/quadcbank/user-dashboard/view-address"
					class="list-group-item list-group-item-action">View Address</a> <a
					href="${request.contextPath}/quadcbank/user-dashboard/account-details"
					class="list-group-item list-group-item-action">Account Details</a>
				<a href="" class="list-group-item list-group-item-action disabled"
					tabindex="-1" aria-disabled="true">Transfer Fund</a> <a
					href="${request.contextPath}/quadcbank/logout"
					class="list-group-item list-group-item-action">Logout</a>
			</div>
		</div>

		<div class="col-sm-8 col-lg-9">
			<div class="jumbotron">
				<h2 class="display-4">Hello, ${customer.firstName}!</h2>
				<p class="lead">Your Recent Account Transactions Details are:</p>
				<hr class="my-4">
				<%-- <p class="lead">
					Account Number: ${customer.account.accountNumber }<br /> Account
					Type: ${customer.account.accountType }<br /> Current Balance:
					$${customer.account.balance }
				</p> --%>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">S.N.</th>
							<th scope="col">Amount</th>
							<th scope="col">Transaction Type</th>
							<th scope="col">Remarks</th>
							<th scope="col">Date</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${customer.account.transactions}" begin="0" end="9" var="transaction" varStatus="loop" >
						<tr>
							<th scope="row">${loop.index + 1}</th>
							<td>${transaction.amount}</td>
							<td>${transaction.transactionType}</td>
							<td>${transaction.remarks}</td>
							<td>${transaction.transactionDate}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
		</div>

	</div>
</div>
<!-- /BODY ENDS HERE -->

<%@include file="common/footer.jsp"%>