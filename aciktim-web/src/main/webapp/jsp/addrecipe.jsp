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
<div class="container">

<div class="row">
  <%@include  file="header-signed-out.jsp" %>
  <%@ include  file="content-bar.jsp" %>

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
        <input type="text" class="form-control" >
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">Browse</button>
        </span>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-lg-6">
      <div class="input-group">
      Please enter the ingredients and their amounts
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-lg-6">
      <div class="input-group">
        <div class="container">
          <table class="table">
            <thead>
            <tr>
              <th>Ingredient</th>
              <th>Amount</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td><button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                Dropdown
                <span class="caret"></span>
              </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li><a href="#">Separated link</a></li>
                </ul></td>
              <td>
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                Dropdown
                <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li><a href="#">Separated link</a></li>
                </ul>
              </td>
            </tr>
            </tbody>
          </table>
        </div>

      </div>
    </div>
  </div>
</div>
</body>
</html>
