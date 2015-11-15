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
        <%@include  file="header-signed-in.jsp" %>
    </c:if>

    <%@include file="content-bar.jsp"%>
    <c:if test="${full_name != ''}">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">

                    <%--<input type="hidden" name="action_type" value="edit">--%>
                    <%--<a href="${contextPath}/recipeform" class="btn btn-primary">Add a new recipe</a>--%>
                    <form action="${contextPath}/recipeform" method="post" class="form-group">
                        <input type="hidden" name="action_type" value="add">
                        <button type="submit" class="btn btn-primary">Add a new recipe</button>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <table class="table table-bordered" style="margin-top:10px;">
                        <thead>
                        <th>Name</th>
                        <th>Picture</th>
                        <th>Description</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        </thead>
                        <tbody>
                        <c:forEach var="recipe" items="${recipes}" varStatus="roop">
                            <tr>
                                <td>${recipe.name}</td>
                                <td>
                                    <img src = "${recipe.pictureAddress}" class = "img-rounded center-block"  width="240">
                                </td>
                                <td>${recipe.description}</td>
                                <td>
                                    <form action="${contextPath}/recipeform" method="post" class="form-group">
                                        <input type="hidden" name="action_type" value="edit">
                                        <input type="hidden" name="recipe_id" value="${recipe.id}">
                                        <button type="submit" class="btn btn-primary">Edit Recipe</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="${contextPath}/recipe/delete" method="post" class="form-group">
                                        <input type="hidden" name="recipe" value="${recipe.id}">
                                        <button type="submit" class="btn btn-danger">Delete Recipe</button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>


    </c:if>

    <%@include  file="footer.jsp" %>

</div>

</body>
</html>