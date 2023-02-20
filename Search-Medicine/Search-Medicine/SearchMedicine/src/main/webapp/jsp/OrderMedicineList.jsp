<%@page import="com.Medicine.Controller.MedicineListCtl"%>
<%@page import="com.Medicine.Bean.MedicineBean"%>
<%@page import="com.Medicine.Controller.UserListCtl"%>
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
<title>Order Medicine List</title>
</head>
<body>

<%@include file="Header.jsp"%>
	<h2 align="center">Medicine List</h2>
	<br>
	<%
		UserBean bean2 = (UserBean) session.getAttribute("user");
	%>
	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=SMView.ORDER_MEDICINE_LIST_CTL%>" method="post">

		<%-- <table width="100%">
			<tr>
				<td align="center"><label>Medicine Name :</label> <input
					type="text" name="mName" placeholder="Enter Medicine Name"
					value="<%=ServletUtility.getParameter("mName", request)%>">
					&emsp;&emsp; <input type="submit" name="operation" 
					value="<%=MedicineListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input type="submit"
					name="operation" value="<%=MedicineListCtl.OP_RESET%>"></td>
			</tr>
		</table> --%>
		<br>

		<table class="table table-striped">
			<tr >
				
				<th scope="col" style="color: blue">ID</th>
				<th scope="col"  style="color: blue">Medicine Name</th>
				<th scope="col"style="color: blue">Medicine Id</th>
				<th scope="col"style="color: blue">Quantity</th>
			<!-- 	<th scope="col">UserName</th> -->
				<!-- <th scope="col"style="color: blue">Action</th> -->
					<th scope="col"></th>
			</tr>
			<%
			
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MedicineBean bean = (MedicineBean) it.next();
			%>
			<tr>
				
				<th scope="row" style="color: blue"><%=index++%></th>
				<td><%=bean.getMedicineName()%></td>
				<td><%=bean.getMedicineid()%></td>
				<td><%=bean.getQuantity()%></td>
				<%-- <td><%=bean.getUserid()%></td> --%>
				<%-- <td><a class="btn btn-danger" href="<%=SMView.MEDICINE_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
				--%>
				<%if(bean2.getRoleid()==2){%>
				
				<td><a class="btn btn-success"
					href="<%=SMView.PAYMENT_CTL%>">Pay</a></td> 
					<%}else{ %>
<%} %>					
				<%}%>
			</tr>
			
		
			
			</tbody>
		</table>

		
	</form>
</body>
<%@include file="Footer.jsp"%>


</body>
</html>