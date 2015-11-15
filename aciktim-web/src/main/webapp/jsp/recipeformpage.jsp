<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="text/javascript" src="${contextPath}/assets/jquery/jquery-1.11.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-out-style.css"/>
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
</head>

<%--recipe.name,recipe.ownerID,recipe.IngredientAmountMap,recipe.pictureAddress,recipe.description--%>

<body>
<div class="container">
    <c:if test="${full_name == ''}">
        <%@include  file="header-signed-out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include file="header-signed-in.jsp"%>
    </c:if>



    <%@include file="content-bar.jsp"%>



    <c:if test="${full_name != ''}">
        <%@include  file="recipeform.jsp" %>
    </c:if>
    <c:if test="${full_name == ''}">
        <%@include  file="logintosee.jsp" %>
    </c:if>



    <%@include  file="footer.jsp" %>
</div>
</body>
</html>
