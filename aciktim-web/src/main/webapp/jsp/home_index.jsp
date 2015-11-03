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
        <div class="container text-center" id="header-signed-out">
            <div class="row" id="header-main-row">
                <div class="col-md-6" id="header-col1">
                        <%--<a href="/aciktim/index" id="page-name" >Acıktım</a>--%>
                    <h1 id="page-name">Acıktım</h1>
                </div>
                <div class="col-md-6" id="header-col2">
                    <div class="row">
                        <div class="navbar-form">
                            <div class="form-group">
                                Welcome ${full_name}!
                                <a href="${contextPath}/logout" class="btn btn-primary">Logout</a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-right">
                            <a id="forgot-password" href="">Forgot Password</a>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </c:if>

    <%@include file="content-bar.jsp"%>

    <c:if test="${full_name != ''}">
        <div class="container">

            <div class="row">
                <div class="col-md-3">

                    <div class = "row">
                        <h3>My Profile</h3>
                    </div>

                    <div class = "row">
                        <!-- change the dummy link -->
                        <img src = "http://placehold.it/240x320" class = "img-rounded center-block"  width="240" height="320">
                    </div>
                    <div class = "row">
                        <!-- change the dummy name and surname -->
                        <h4 class="text-center">${full_name}</h4>

                        <table class = "table">
                            <tr>
                                <th><b>Followers:</b></th>
                                <!-- change the dummy numbers -->
                                <td>1500</td>
                            </tr>
                            <tr>
                                <th><b>Followings:</b></th>
                                <!-- change the dummy numbers -->
                                <td>700</td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class = "row">
                        <h3>Recent Activities</h3>
                    </div>
                    <div class = "row">
                        <!-- change the dummy list items, this can be scrollable -->
                        <ul class = "list-group">
                            <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                            <li class = "list-group-item">Posted a new recipe: Tagliatelle Pollo</li>
                            <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                            <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                            <li class = "list-group-item">Posted a new recipe: Tagliatelle Pollo</li>
                            <li class = "list-group-item">Liked a new restaurant: J'adore Chocolatier</li>
                        </ul>
                    </div>
                    <div class = "row">
                        <h3>Preferences</h3>
                    </div>
                    <div class = "row">
                        <div class="col-md-6">
                            <form role = "form">
                                <div class = "form-group">
                                    <input type = "text" class = "form-control" placeholder = "Enter a preference">
                                </div>
                            </form>
                        </div>
                        <div class="col-md-3">
                            <button type="button" class="btn btn-warning btn-block">Add</button>
                        </div>
                        <div class="col-md-3">
                            <button type="button" class="btn btn-warning btn-block">See Daily Consumed List</button>
                        </div>
                    </div>
                    <div class = "row">
                        <form role = "form">
                            <div class = "form-group">
                                <textarea class = "form-control" rows = "3" placeholder = "Some tags"></textarea>
                            </div>
                        </form>
                    </div>

                </div>


                <div class="row">
                    <div class="col-md-3">
                        <div class = "row">
                            <h4>Recommended:</h4>
                        </div>
                        <div class = "row">
                            <!-- change the dummy numbers -->
                            <table class = "table">
                                <tr>
                                    <th>Image Here</th>
                                    <td>O La La Beatrice</td>
                                </tr>
                                <tr>
                                    <th>Image Here</th>
                                    <td>Tagliatelle Pollo</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class = "row">
                            <h4>My Recipes:</h4>
                        </div>
                        <div class = "row">
                            <!-- change the dummy numbers -->
                            <table class = "table">
                                <tr>
                                    <th>Image Here</th>
                                    <td>O La La Beatrice</td>
                                </tr>
                                <tr>
                                    <th>Image Here</th>
                                    <td>Tagliatelle Pollo</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class = "row">
                            <h4>Preferred Food:</h4>
                        </div>
                        <div class = "row">
                            <!-- change the dummy numbers -->
                            <table class = "table">
                                <tr>
                                    <th>Image Here</th>
                                    <td>O La La Beatrice</td>
                                </tr>
                                <tr>
                                    <th>Image Here</th>
                                    <td>Tagliatelle Pollo</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class = "row">
                            <h4>Favorite Recipes:</h4>
                        </div>
                        <div class = "row">
                            <!-- change the dummy numbers -->
                            <table class = "table">
                                <tr>
                                    <th>Image Here</th>
                                    <td>O La La Beatrice</td>
                                </tr>
                                <tr>
                                    <th>Image Here</th>
                                    <td>Tagliatelle Pollo</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


        </div> <!-- /container -->
    </c:if>


    <%@include  file="footer.jsp" %>

</div>

</body>
</html>