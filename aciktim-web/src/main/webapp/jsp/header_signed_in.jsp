<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="requestURI" value="${pageContext.request.requestURI}" scope="application"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-in-style.css"/>
</head>
<body>
<div class="container text-center" id="header-signed-in">
    <div class="row" id="header-main-row">
        <div class="col-md-6" id="header-col1">
            <div id="page-name">
                <img src="${contextPath}/assets/img/aciktim_logo.png" alt=""/>
            </div>
        </div>
        <div class="col-md-6 text-right" id="header-col2">
            <div class="form-group">
                <span id="welcome">Welcome ${full_name}!</span>
                <a href="${contextPath}/logout" class="btn btn-primary">Logout</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
