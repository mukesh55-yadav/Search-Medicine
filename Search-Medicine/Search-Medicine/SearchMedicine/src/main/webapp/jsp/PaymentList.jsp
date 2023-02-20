<%@page import="com.Medicine.Bean.PaymentBean"%>
<%@page import="com.Medicine.Utility.ServletUtility"%>
<%@page import="com.Medicine.Utility.DataUtility"%>
<%@page import="com.Medicine.Controller.SMView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PaymentList</title>
</head>
<body>



<%@include file="Header.jsp"%>
	<h2 align="center">Payment List</h2>
	<br>
	<%
		UserBean bean2 = (UserBean) session.getAttribute("user");
	%>
	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=SMView.PAYMENT_CTL%>" method="post">
		<br>

		<table class="table table-striped">
			<tr >
				
				<th scope="col" style="color: blue">ID</th>
				<th scope="col"  style="color: blue">Amount</th>
				<th scope="col"style="color: blue">Card Number</th>
				<th scope="col"style="color: blue">OrderId</th>
			<!-- 	<th scope="col">UserName</th> -->
				<!-- <th scope="col"style="color: blue">Action</th> -->
					<th scope="col"></th>
			</tr>
			<%
			
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					PaymentBean bean = (PaymentBean) it.next();
			%>
			<tr>
				
				<th scope="row" style="color: blue"><%=index++%></th>
				<td><%=bean.getAmount()%></td>
				<td><%=bean.getCardNumber()%></td>
				<td><%=bean.getOrderid()%></td>
				<%}%>
			</tr>
			
		
			
			</tbody>
		</table>

		
	</form>
</body>
<%@include file="Footer.jsp"%>



</body>
</html>