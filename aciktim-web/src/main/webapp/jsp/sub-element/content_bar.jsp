<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" id="content-bar">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>
    <div class="row">
        <div class="col-md-6 text-left">
            <ul class="nav nav-pills">
                <li role="presentation" class="active"><a href="/aciktim">Home</a></li>
                <li role="presentation"><a href="#">Restaurants</a></li>
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
