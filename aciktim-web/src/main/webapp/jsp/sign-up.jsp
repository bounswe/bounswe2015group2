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
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/sign-up-style.css"/>
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" id="main-container">

    <%@include  file="header-signed-out.jsp" %>

    <%@include  file="content-bar.jsp" %>

    <div class="container" id="sign-up">
        <div class="container">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#">Regular User</a></li>
                <li role="presentation"><a href="#">Institutional User</a></li>
            </ul>
        </div>

        <div class="container">

            <c:if test="${type == 'ERROR'}">
                <div class="alert alert-danger">
                    <strong>ERROR:</strong> Check your data.
                </div>
            </c:if>
            <!-- ${type} -->


            <form action="${contextPath}/adduser" method="post" class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">First Name : </span>
                    <input type="text" name="first_name" class="form-control" placeholder="First Name" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">Last Name : </span>
                    <input type="text" name="last_name" class="form-control" placeholder="Last Name" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">E-mail : </span>
                    <input type="email" name="email" class="form-control" placeholder="Email" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">Confirm E-mail: </span>
                    <input type="email" name="confirm_email" class="form-control" placeholder="Email" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">Password: </span>
                    <input type="password" name="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">Confirm Password: </span>
                    <input type="password" name="confirm_password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
                </div>

                <div class="input-group">
                    <button type="submit" class="btn btn-success">Register</button>
                </div>

            </form>
        </div>
    </div>


</div>








<%@include  file="footer.jsp" %>

</div>



<script>
    jQuery(document).ready(function ($) {
        $('.nav-tabs').tab();
    });
</script>

</body>
</html>