<%@page import="com.Medicine.Controller.SMView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<%@include file="Header.jsp"%>

<div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<%=SMView.APP_CONTEXT%>/img/sm1.jpg" class="d-block w-100" alt="..."  width="500" 
     height="700">
    </div>
    <div class="carousel-item">
      <img src="<%=SMView.APP_CONTEXT%>/img/sm2.jpg" class="d-block w-100" alt="..."  width="500" 
     height="700">
    </div>
    <div class="carousel-item">
      <img src="<%=SMView.APP_CONTEXT%>/img/sm3.jpg" class="d-block w-100" alt="..."  width="500" 
     height="700">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<%@include file="Footer.jsp"%>

</body>
</html>