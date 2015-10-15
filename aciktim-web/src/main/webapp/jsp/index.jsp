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
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

    <style>
        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="starter-template">
    <h1>Acıktım</h1>

    <p class="lead"></p>
</div>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-primary btn-sm" href="${contextPath}/new-user">New User</a>
        <table class="table table-bordered" style="margin-top:10px;">
            <thead>
            <th>#</th>
            <th>Email</th>
            <th>Password</th>
            <th>Edit</th>
            <th>Delete</th>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="roop">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.passwd}</td>
                    <td><a class="btn btn-primary btn-sm" href="${contextPath}/user/edit?id=${user.id}">Edit</a></td>
                    <td><a class="btn btn-danger btn-sm" href="${contextPath}/user/delete?id=${user.id}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>