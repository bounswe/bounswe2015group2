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
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-out-style.css"/>
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container text-center" id="header-signed-out">
    <div class="row" id="header-main-row">
        <div class="col-md-6" id="header-col1">
            <%--<a href="/aciktim/index" id="page-name" >Acıktım</a>--%>
            <h1 id="page-name">Acıktım</h1>
        </div>
        <div class="col-md-6" id="header-col2">
            <div class="row">
                <form class="navbar-form">
                    <div class="form-group">
                        <input type="text" placeholder="Email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control">
                        <button type="submit" class="btn btn-success">Sign in</button>
                        <a href="signup" class="btn btn-primary">Sign up</a>

                    </div>

                </form>
            </div>
            <div class="row">
                <div class="col-md-12 text-right">
                    <a id="forgot-password" href="">Forgot Password</a>
                </div>

            </div>

        </div>
    </div>


</div>

</body>
</html>