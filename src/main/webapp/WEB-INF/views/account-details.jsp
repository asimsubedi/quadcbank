<%@include file="common/header.jsp"%>
<%@include file="common/nav.jsp"%>

<!-- BODY STARTS HERE -->
<div class="container" style="margin-top: 40px">
	<div class="row">

		<%@include file="common/admin-sidemenu.jsp"%>
		
		<div class="col-sm-8 col-lg-9">
			<c:if test="${param.transfer eq 'success'}">
                <div class="alert alert-primary">
					<strong>Fund Transfer Success!!!</strong>
				</div>
            </c:if>
			<div class="jumbotron">
				<h2 class="display-4">Hello, ${customer.firstName}!</h2>
				<p class="lead">Your Account Details is:</p>
				<hr class="my-4">
				<p class="lead">
					Account Number: ${customer.account.accountNumber }<br /> Account
					Type: ${customer.account.accountType }<br /> Current Balance:
					$${customer.account.balance }
				</p>
			</div>
		</div>

	</div>
</div>
<!-- /BODY ENDS HERE -->

<%@include file="common/footer.jsp"%>