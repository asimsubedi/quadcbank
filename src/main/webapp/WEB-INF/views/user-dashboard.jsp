<%@include file="common/header.jsp" %>
<%@include file="common/nav.jsp" %>

	<!-- BODY STARTS HERE -->
	<div class="container" style="margin-top: 40px">
		<div class="row">
		
			<div class="col-sm-4 col-lg-3">
				<div class="list-group list-group-flush">
					<a href="user-dashboard" class="list-group-item list-group-item-action">Dashboard</a>
					<a href="" class="list-group-item list-group-item-action">Balance Inquiry</a>
					<a href="" class="list-group-item list-group-item-action">View Address</a>
					<a href="account-details" class="list-group-item list-group-item-action">Account Details</a>
					<a href="" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">Transfer Fund</a>
					<a href="logout" class="list-group-item list-group-item-action">Logout</a>
				</div>
			</div>
			
			<div class="col-sm-8 col-lg-9">
				<div class="jumbotron">
					<h2 class="display-4">Hello, ${customer.firstName}!</h2>
					<p class="lead">Welcome to your account Dashboard. </p>
					<hr class="my-4">
					<p class="lead">
						<a class="btn btn-primary btn-sm" href="account-details" role="button"> VIEW ACCOUNT DETAILS &rarr; </a>
					</p>
				</div>
			</div>
			
		</div>
	</div>
	<!-- /BODY ENDS HERE -->

<%@include file="common/footer.jsp" %>