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

    <script type="text/javascript" src="${contextPath}/assets/jquery/jquery-1.11.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-out-style.css"/>
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <form action="${contextPath}/recipe/${action_type}" method="post" class="form-group">
        <div class="row">
            <div class="input-group">
                <span class="input-group-addon">Recipe Name : </span>
                <input type="text" name="recipe_name" class="form-control" placeholder="e.g. Saksuka" aria-describedby="basic-addon1" value="${existing_recipe_name}">
            </div>
        </div>

        <div class="row">
            <div class="input-group">
                <span class="input-group-addon">Description : </span>
                <input type="text" name="description" class="form-control" placeholder="Description" aria-describedby="basic-addon1" value="${existing_recipe_description}">
            </div>

        </div>

        <div class="row">
            <div class="input-group">
                <span class="input-group-addon">Image Url: </span>
                <input type="text" name="image_url" class="form-control" placeholder="Url" aria-describedby="basic-addon1" value="${existing_recipe_image_url}">
            </div>

        </div>

        <div class="row">
            
            
            <div class="input-group">

                <input type="hidden" name="recipe_id" value="${existing_recipe_id}">
                <button type="submit" class="btn btn-success">${action_type}</button>
            </div>

        </div>




    </form>
</div>

</body>
</html>
