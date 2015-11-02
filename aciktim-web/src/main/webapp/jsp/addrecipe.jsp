<%--
  Created by IntelliJ IDEA.
  User: eren
  Date: 02/11/15
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="requestURI" value="${pageContext.request.requestURI}" scope="application"/>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Acıktım</title>

  <script type="text/javascript" src="${contextPath}/assets/jquery/jquery-1.11.2.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
  <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<%@include  file="header-signed-out.jsp" %>
<%@ include  file="content-bar.jsp" %>


<div class="container">
  <div class="row">
    <div class="col-lg-6">
      <div class="input-group">
        <span class="input-group-addon" >enter the name</span>
        <input type="text" name="recipeName" class="form-control" placeholder="Name for recipe">
      </div>
    </div>
  <div class="col-lg-6">
    <div class="input-group">
        <span class="input-group-addon" >enter the category</span>
        <input type="text"  class="form-control" placeholder="Category for the recipe">
    </div>
  </div>
</div>
<div class="row">
  <div class="col-lg-6">
    <div class="input-group">
      <span class="input-group-addon" >Photo</span>
      <input type="text" class="form-control" placeholder="photo.png" >
      <span class="input-group-btn">
        <button class="btn btn-warning btn-block" type="button">Browse</button>
      </span>
    </div>
  </div>
</div>
  <div class="row">
    <div class="col-lg-6">
      Please enter the ingredients and their amounts
      <div class="col-lg-6">
      </div>
      <div class="col-lg-3">
        <div class="dropdown">
          <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            Ingredients
          <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" >
            <li><a href="#">testCarbonhydrate</a></li>
            <li><a href="#">testProtein</a></li>
          </ul>
        </div>
      </div>
      <div class="col-lg-3">
        <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          Amount
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" >
          <li><a href="#">testAmount</a></li>
          <li><a href="#">testAmount2</a></li>
        </ul>
        </div>

      </div>
    </div>

  </div>
  <div class="col-md-3">
    <button type="button" class="btn btn-warning btn-block">Add</button>
  </div>
  <div class="col-md-3">
    <button type="button" class="btn btn-warning btn-block">Calculate the nutritional data</button>
  </div>

</div>

<div class="col-md-6">
  </div>
<div class="col-md-6">
<div class = "row">
  Tags
</div>
<div class = "row">
  <div class="col-md-6">
    <form role = "form">
      <div class = "form-group">
        <input type = "text" class = "form-control" placeholder = "Enter a tag">
      </div>
    </form>
  </div>
  <div class="col-md-3">
    <button type="button" class="btn btn-warning btn-block">Add</button>
  </div>

</div>
<div class = "row">
  <div class="col-md-6">
  <form role = "form">
    <div class = "form-group">
      <textarea class = "form-control" rows = "3" placeholder = "Some tags"></textarea>
    </div>

  </form>
  </div>
</div>
</div>
</body>
</html>
