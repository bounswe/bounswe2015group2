<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container text-center" id="header-signed-in">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/header-signed-in-style.css"/>
    <div class="row" id="header-main-row">
        <div class="col-md-6" id="header-col1">
            <div id="page-name">
                <img src="${contextPath}/assets/img/aciktim_logo.png" alt=""/>
            </div>
        </div>
        <div class="col-md-6 text-right" id="header-col2">
            <div class="form-group" id="welcome_group">
                <span id="welcome">Welcome ${full_name}!</span>
                <a href="${contextPath}/logout" class="btn btn-primary">Logout</a>
            </div>
        </div>
    </div>
</div>

