<%@ page import="java.net.CookieManager" %>
<%@ page import="java.net.CookieStore" %>
<%@ page import="java.net.CookieHandler" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="requestURI" value="${pageContext.request.requestURI}" scope="application"/>

<!DOCTYPE html>
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
<div class="container" id="main-container">


    <c:if test="${full_name == ''}">
        <%@include  file="header-signed-out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include file="header-signed-in.jsp"%>
    </c:if>



    <%@include file="content-bar.jsp"%>



    <c:if test="${full_name != ''}">
        <%@include  file="profile.jsp" %>
    </c:if>
    <c:if test="${full_name == ''}">
        <%@include  file="logintosee.jsp" %>
    </c:if>



    <%@include  file="footer.jsp" %>

</div>

</body>
</html>