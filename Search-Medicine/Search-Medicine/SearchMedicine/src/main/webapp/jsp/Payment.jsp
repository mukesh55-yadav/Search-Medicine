<%@page import="com.Medicine.Controller.PaymentCtl"%>
<%@page import="com.Medicine.Utility.ServletUtility"%>
<%@page import="com.Medicine.Utility.DataUtility"%>
<%@page import="com.Medicine.Controller.SMView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>

<%@include file="Header.jsp"%>
	<div class="container">
		<br>
		
		<h2 class="text-center">Payment</h2>
		<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
		<hr>
		<form action="<%=SMView.PAYMENT_CTL%>" method="post">

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">


					<jsp:useBean id="bean" scope="request"
						class="com.Medicine.Bean.PaymentBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">

					<div class="mb-3">
						<label class="form-label">Amount</label> <input type="text"
							class="form-control" name="amount" placeholder="Enter Amount here...">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("amount", request)%></div>
					</div>


					<div class="mb-3">
						<label class="form-label">Card Number</label> <input type="text"
							class="form-control" name="cardNumber"
							placeholder="Enter Card Number here..."">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("cardNumber", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">Order Id</label> <input type="text"
							class="form-control" name="orderId"
							placeholder="Enter Order Id here...">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("orderId", request)%></div>
					</div>

					

<div class="container text-center">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=PaymentCtl.OP_PAY%>">
</div>
				</div>
				<div class="col-2"></div>

			</div>
		</form>
	</div>
	<br>
	<%@ include file="Footer.jsp"%>





</body>
</html>