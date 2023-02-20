<%@page import="com.Medicine.Controller.MedicineOrderCtl"%>
<%@page import="com.Medicine.Controller.AddMedicineCtl"%>
<%@page import="com.Medicine.Utility.ServletUtility"%>
<%@page import="com.Medicine.Utility.DataUtility"%>
<%@page import="com.Medicine.Controller.SMView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Medicine</title>
</head>
<body>


<%@include file="Header.jsp"%>
	<%
		UserBean bean2 = (UserBean) session.getAttribute("user");
	%>
	<div class="container">
		<br>
		<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
		
		<form action="<%=SMView.ORDER_MEDICINE_CTL%>" method="post">

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">


					<jsp:useBean id="bean" scope="request"
						class="com.Medicine.Bean.MedicineBean" />
					<input type="hidden" name="id" value="<%=bean.getId()%>"> 
					<h1 class="text-center">Book Medicine</h1>

<hr>
					<div class="mb-3">
						<label class="form-label">Medicine Name</label> <input type="text"
							class="form-control" name="mName"
							placeholder="Enter Medicine Name here..."
							value="<%=DataUtility.getStringData(bean.getMedicineName())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("mName", request)%></div>
					</div>
					
					<div class="mb-3">
						<label class="form-label">Medicine Id</label> <input type="text"
							class="form-control" name="mId" placeholder="Enter Medicine Id here...">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("mId", request)%></div>
					</div>
					
					<div class="mb-3">
						<label class="form-label">Quantity</label> <input type="text"
							class="form-control" name="quantity"
							placeholder="Enter Quantity here..."
							value="<%=DataUtility.getStringData(bean.getQuantity())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%></div>
					</div>


<div class="mb-3">
						<label class="form-label">UserId</label> <input type="text"
							class="form-control" name="userId"
							placeholder="Enter User here..."
							value="<%=DataUtility.getStringData(bean2.getEmail())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("userId", request)%></div>
					</div>


<div class="container text-center">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=MedicineOrderCtl.OP_SAVE%>">
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