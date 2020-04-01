<%@include file="common/header.jsp" %>
<%@include file="common/nav.jsp" %>

<!-- BODY STARTS HERE -->
<div class="container" style="margin-top: 40px">
	<div class="row">
		<div class="col-sm-4">
		
			<c:if test="${errorMsg != null}">
				<div class="alert alert-warning">
					<strong>${errorMsg}</strong>
				</div>
			</c:if>
			
			<c:if test="${param.action eq 'logout'}">
                <div class="alert alert-primary">
					<strong>Successfully Logged Out!! Thank You for Visiting.</strong>
				</div>
            </c:if>
            
			<form action="login" method="post">
				<div class="form-group">
					<label for="email">Email address</label>
					<input type="email" name="email" class="form-control" id="email" value="niv@tek.dev" aria-describedby="emailHelp"
						placeholder="Enter email"> <small id="emailHelp"
						class="form-text text-muted">We'll never share your email
						with anyone else.</small>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" name="password"
						class="form-control" id="password" placeholder="Password">
				</div>
				<button type="submit" class="btn btn-primary">LOGIN &rarr;</button>
			</form>
		</div>
	</div>
</div>
<!-- /BODY ENDS HERE -->

<%@include file="common/footer.jsp" %>