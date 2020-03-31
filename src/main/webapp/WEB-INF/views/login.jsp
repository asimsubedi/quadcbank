<jsp:include page="common/header.jsp"/>
<jsp:include page="common/nav.jsp"/>

	<!-- BODY STARTS HERE -->
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-4">
				<form action="login" method="post">
					<div class="form-group">
						<label for="email">Email address</label> <input
							type="email" class="form-control" id="email"
							aria-describedby="emailHelp" placeholder="Enter email"> <small
							id="emailHelp" class="form-text text-muted">We'll never
							share your email with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input
							type="password" class="form-control" id="password"
							placeholder="Password">
					</div>
					<button type="submit" class="btn btn-primary">LOGIN &rarr;</button>
				</form>
			</div>
		</div>
	</div>
	<!-- /BODY ENDS HERE -->

<jsp:include page="common/footer.jsp" />