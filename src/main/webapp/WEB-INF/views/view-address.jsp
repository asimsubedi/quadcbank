<%@include file="common/header.jsp"%>
<%@include file="common/nav.jsp"%>

<!-- BODY STARTS HERE -->
<div class="container" style="margin-top: 40px">
	<div class="row">

		<%@include file="common/admin-sidemenu.jsp"%>

		<div class="col-sm-8 col-lg-9">
			<div class="jumbotron">
				<h2 class="display-4">Hello, ${customer.firstName}!</h2>
				<p class="lead">Your Current Address is:</p>
				<hr class="my-4">
				<address>
					${customer.address.line1 } ${customer.address.line2}<br/>
					${customer.address.city }, ${customer.address.state }, ${customer.address.zip }
				</address>
				<br/>
				<button data-toggle="collapse" data-target="#editAddress" class="btn btn-sm btn-primary"> UPDATE MY ADDRESS ></button>
				<%-- <a href="${request.contextPath}/quadcbank/user-dashboard/edit-address?edit=${customer.customerId}"> Edit Address > </a> --%>
				
				<div id="editAddress" class="collapse">
					<div class="col-sm-6 bg-light" style="padding:15px; margin-top: 20px;">
						
						<form action="update-address?cust=${customer.customerId}" method="post">
						
							<div class="form-group">
								<label for="line1">Line1</label>
								<input type="text" value="${customer.address.line1}" name="line1" class="form-control" placeholder="Line1" id="line1" required>
							</div>
							
							<div class="form-group">
								<label for="line1">Line2</label>
								<input type="text" value="${customer.address.line2}" name="line2" class="form-control" id="line2" required>
							</div>
							
							<div class="form-group">
								<label for="city">city</label>
								<input type="text" value="${customer.address.city}" name="city" class="form-control" id="city" required>
							</div>
							
							<div class="form-group">
								<label for="state">state</label>
								<input type="text" value="${customer.address.state}" name="state" class="form-control" id="state" required>
							</div>
							
							<div class="form-group">
								<label for="zip">zip</label>
								<input type="text" value="${customer.address.zip}" name="zip" class="form-control" id="zip" required>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-outline-primary"> UPDATE ADDRESS > ></button>

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