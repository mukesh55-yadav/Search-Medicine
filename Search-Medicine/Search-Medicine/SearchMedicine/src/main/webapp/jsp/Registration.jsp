<%@page import="com.Medicine.Utility.ServletUtility"%>
<%@page import="com.Medicine.Utility.DataUtility"%>
<%@page import="com.Medicine.Controller.SMView"%>
<%@page import="com.Medicine.Controller.RegistrationCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RegistrationView</title>
</head>
<body>
<%@include file="Header.jsp"%>
	
	<div class="container">
		<br>
		<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=SMView.WECOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">User Registration</li>
			</ol>
		</nav>
		<h2 class="text-center">User Registration</h2>
		<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
		<hr>
		<form action="<%=SMView.REGISTRATION_CTL%>" method="post">

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">


					<jsp:useBean id="bean" scope="request"
						class="com.Medicine.Bean.UserBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">

					<div class="mb-3">
						<label class="form-label">UserName</label> <input type="text"
							class="form-control" name="username" placeholder="Enter Name here..."
							value="<%=DataUtility.getStringData(bean.getUserName())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("username", request)%></div>
					</div>


					<div class="mb-3">
						<label class="form-label">Email Id</label> <input type="text"
							class="form-control" name="email"
							placeholder="Enter Email Id here..."
							value="<%=DataUtility.getStringData(bean.getEmail())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">Password</label> <input type="password"
							class="form-control" name="password"
							placeholder="Enter Password here..."
							value="<%=DataUtility.getStringData(bean.getPassword())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></div>
					</div>

					

<div class="container text-center">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=RegistrationCtl.OP_SAVE%>"> &nbsp;&nbsp;<input
						type="submit" class="btn btn-primary" name="operation"
						value="<%=RegistrationCtl.OP_RESET%>">
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