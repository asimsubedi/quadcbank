<%@include file="common/header.jsp"%>
<%@include file="common/nav.jsp"%>

<!-- BODY STARTS HERE -->
<div class="container" style="margin-top: 40px">
	<div class="row">

		<%@include file="common/admin-sidemenu.jsp"%>

		<div class="col-sm-8 col-lg-9">
			<div class="jumbotron">
				<h2 class="display-4">Hello, ${customer.firstName}!</h2>
				<p class="lead">Welcome to your account Dashboard.</p>
				<hr class="my-4">
				<p class="lead">
					<a class="btn btn-primary btn-sm"
						href="${request.contextPath}/quadcbank/user-dashboard/account-details"
						role="button"> VIEW ACCOUNT DETAILS &rarr; </a>
				</p>
			</div>
		</div>

	</div>
</div>
<!-- /BODY ENDS HERE -->

<%@include file="common/footer.jsp"%>