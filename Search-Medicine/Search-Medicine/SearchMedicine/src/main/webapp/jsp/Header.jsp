<%@page import="com.Medicine.Controller.RegistrationCtl"%>
<%@page import="com.Medicine.Controller.LoginCtl"%>
<%@page import="com.Medicine.Bean.UserBean"%>
<%@page import="com.Medicine.Controller.SMView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HeaderView</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>


</head>
<body>

	<%
		UserBean userBean = (UserBean) session.getAttribute("user");
	%>
	<%
		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hello, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			//String lastname =  userBean.getLastName();
			welcomeMsg += userBean.getRoleName();
		} else {
			welcomeMsg += "Guest";
		}
	%>

	<!-- As a heading -->
	<nav class="navbar bg-light">
		<span class="navbar-brand mb-0 h1" style="color: teal;">SearchMedicine
		</span>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="<%=SMView.WECOME_CTL%>">Home</a></li>
				<%-- 
				<li class="nav-item active"><a class="nav-link" href="<">View
						Blog</a></li> --%>



				<%
					if (userBean != null) {
				%>

				<%
					if (userBean.getRoleid() == 1) {
				%>




				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> User </a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=SMView.USER_CTL%>">Add
										User</a></li>
								<li><a class="dropdown-item"
									href="<%=SMView.USER_LIST_CTL%>">UserList</a></li>
							</ul></li>
					</ul>
				</div>


				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">MediCine </a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item"
									href="<%=SMView.MEDICINE_CTL%>">Add MediCine</a></li>
								<li><a class="dropdown-item"
									href="<%=SMView.MEDICINE_LIST_CTL%>">View MediCine list</a></li>
							</ul></li>
					</ul>
				</div>

				<li class="nav-item"><a class="nav-link"
					href="<%=SMView.ORDER_MEDICINE_LIST_CTL%>">Medicine Booking</a></li>
					
						<li class="nav-item"><a class="nav-link"
					href="<%=SMView.PAYMENT_LIST_CTL%>">Payment History</a></li>
					
					
					
				<%
					} else if (userBean.getRoleid() == 2) {
				%>

				<li class="nav-item"><a class="nav-link"
					href="<%=SMView.MEDICINE_LIST_CTL%>">MediCine</a></li>

				<li class="nav-item"><a class="nav-link"
					href="<%=SMView.ORDER_MEDICINE_LIST_CTL%>">Booking</a></li>

				<li class="nav-item"><a class="nav-link"
					href="<%=SMView.PAYMENT_LIST_CTL%>">Payment History</a></li>

				<%
					}
				%>
				<%
					}
				%>

			</ul>

		</div>



		<ul class="nav justify-content-end">

			<%
				if (userBean == null) {
			%>
			<ul class="nav justify-content-end" style="margin-right: 30px;">
				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">Guest</a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=SMView.LOGIN_CTL%>">SingIn</a></li>
								<li><a class="dropdown-item"
									href="<%=SMView.REGISTRATION_CTL%>">SingUp</a></li>
							</ul></li>
					</ul>
				</div>
			</ul>
			<%
				} else {
			%>
			<ul class="nav justify-content-end" style="margin-right: 30px;">
				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"><%=welcomeMsg%></a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item"
									href="<%=SMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOGOUT%>">Logout</a></li>

							</ul></li>
					</ul>
				</div>
			</ul>


			<%
				}
			%>

		</ul>
	</nav>
</body>
</html>