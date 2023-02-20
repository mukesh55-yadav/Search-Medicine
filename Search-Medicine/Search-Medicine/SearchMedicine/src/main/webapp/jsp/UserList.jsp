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
<title>User List</title>
</head>
<body>

<%@include file="Header.jsp"%>
	<br>
	<h2 align="center">User List</h2>
	<br>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=SMView.USER_LIST_CTL%>" method="post">

		<table width="100%">
			<tr>
				<td align="center"><label>User Name :</label> <input
					type="text" name="username" placeholder="Enter User Name"
					value="<%=ServletUtility.getParameter("username", request)%>">
					&emsp;&emsp; <input type="submit" name="operation" 
					value="<%=UserListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input type="submit"
					name="operation" value="<%=UserListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		<br>

		<table class="table table-striped">
			<tr >
				
				<th scope="col" style="color: blue">ID</th>
				<th scope="col"  style="color: blue">UserName</th>
				<th scope="col"style="color: blue">Email</th>
				<th scope="col"style="color: blue">RoleName</th>
				<th scope="col"style="color: blue">Action</th>
					<th scope="col"></th>
			</tr>
			<%
			
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					UserBean bean = (UserBean) it.next();
			%>
			<tr>
				
				<th scope="row" style="color: blue"><%=index++%></th>
				<td><%=bean.getUserName()%></td>
				<td><%=bean.getEmail()%></td>
			<%
					if (bean.getRoleName().equalsIgnoreCase("ADMIN")) {
				%>
				<td>-------</td>

				<%
					} else {
				%>
				<td><%=bean.getRoleName()%></td>
				
				<td><a class="btn btn-info"
					href="<%=SMView.USER_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
				<td><a class="btn btn-danger" href="<%=SMView.USER_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
			</tr>
			<%
			}
		%>
		
			<%
				}
			%>
			</tbody>
		</table>

		
	</form>
</body>
<%@include file="Footer.jsp"%>

</body>
</html>