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

    <script type="text/javascript" src="${contextPath}/assets/jquery/jquery-1.11.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container" id="content-bar">

    <div class="row">

        <div class="col-md-6 text-left">
            <%--<button type="button" class="btn btn-warning active">Home</button>--%>
            <%--<button type="button" class="btn btn-warning">Restaurants</button>--%>
            <%--<button type="button" class="btn btn-warning">Recipes</button>--%>

            <ul class="nav nav-pills">

                <li role="presentation" class="active"><a href="/aciktim">Home</a></li>
                <li role="presentation"><a href="/aciktim/restaurants">Restaurants</a></li>
                <li role="presentation"><a href="/aciktim/recipes">Recipes</a></li>

            </ul>
        </div>

        <div class="col-md-6 text-right">
            <input type="name" class="form-control" id="search-field" placeholder="Search">
            <button type="button" class="btn btn-warning">Search</button>
            <button type="button" class="btn btn-warning">Advanced Search</button>
        </div>

    </div>

</div>
</body>
</html>