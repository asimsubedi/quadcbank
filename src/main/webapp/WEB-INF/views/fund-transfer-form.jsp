<%@include file="common/header.jsp"%>
<%@include file="common/nav.jsp"%>

<!-- BODY STARTS HERE -->
<div class="container" style="margin-top: 40px">
	<div class="row">

		<%@include file="common/admin-sidemenu.jsp"%>

		<div class="col-sm-8 col-lg-9">
			<c:if test="${param.transfer eq 'failure'}">
				<div class="alert alert-primary">
					<strong>Oops!! Couldn't transfer. Please Try Again!!!</strong>
				</div>
			</c:if>

			<div class="jumbotron">
				<h2 class="display-4">Hello, ${customer.firstName}!</h2>
				<p class="lead">You can Transfer Fund within QuadCBank:</p>
				<hr class="my-4">

				<div id="transferFund" class="row">
					<div class="col-sm-6 bg-light"
						style="padding: 15px; margin-top: 20px;">

						<form action="fund-transfer-confirm?cust=${customer.customerId}"
							method="post">

							<div class="form-group">
								<label for="accountNum">Transfer To Account Number:</label> <input
									type="text" name="accountNumber" class="form-control"
									placeholder="CH10011111" id="accountNum" required>
							</div>

							<div class="form-group">
								<label for="amount">Enter Amount to Send:</label> <input
									type="text" name="amount" class="form-control"
									placeholder="$200.00" id="amount" required>
							</div>

							<div class="form-group">
								<label for="remarks">Remarks</label> <input type="text"
									name="remarks" class="form-control" id="remarks">
							</div>

							<div class="form-check">
								<input type="checkbox" class="form-check-input" id="confirm"
									required> <label class="form-check-label" for="confirm">I
									Hereby Confirm to Send the Amount as Stated Above!</label>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-outline-primary">
									TRANSFER FUND NOW > ></button>

							</div>
						</form>

					</div>
				</div>


			</div>
		</div>

	</div>
</div>
<!-- /BODY ENDS HERE -->

<%@include file="common/footer.jsp"%>