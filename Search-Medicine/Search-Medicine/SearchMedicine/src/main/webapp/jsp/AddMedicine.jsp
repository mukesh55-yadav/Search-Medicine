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
<title>Medicine</title>
</head>
<body>

<%@include file="Header.jsp"%>
	
	<div class="container">
		<br>
		<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
		
		<form action="<%=SMView.MEDICINE_CTL%>" method="post">

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">


					<jsp:useBean id="bean" scope="request"
						class="com.Medicine.Bean.MedicineBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">

<%
						if (bean.getId() > 0) {
					%>
					<h1 class="text-center">Update Medicine</h1>
					<%
						} else {
					%>
					<h1 class="text-center">Add Medicine</h1>
					<%
						}
					%>

<hr>
					<div class="mb-3">
						<label class="form-label">Company Name</label> <input type="text"
							class="form-control" name="cName" placeholder="Enter Company here..."
							value="<%=DataUtility.getStringData(bean.getCompanyName())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("cName", request)%></div>
					</div>


					<div class="mb-3">
						<label class="form-label">Medicine Name</label> <input type="text"
							class="form-control" name="mName"
							placeholder="Enter Medicine Name here..."
							value="<%=DataUtility.getStringData(bean.getMedicineName())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("mName", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">Quantity</label> <input type="text"
							class="form-control" name="quantity"
							placeholder="Enter Quantity here..."
							value="<%=DataUtility.getStringData(bean.getQuantity())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%></div>
					</div>

<div class="container text-center">
					<%
						if (bean.getId() > 0) {
					%>
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=AddMedicineCtl.OP_UPDATE%>">
					<%
						} else {
					%>
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=AddMedicineCtl.OP_SAVE%>">
					<%
						}
					%>
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