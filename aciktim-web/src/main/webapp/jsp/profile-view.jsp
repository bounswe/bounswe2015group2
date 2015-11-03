<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="requestURI" value="${pageContext.request.requestURI}" scope="application"/>

<!DOCTYPE html>
<html>
<body>

<div class="container">

    <div class="row">
        <div class="col-md-3">

            <div class = "row">
                <h3>My Profile</h3>
            </div>

            <div class = "row">
                <!-- change the dummy link -->
                <img src = "http://ris.fashion.telegraph.co.uk/RichImageService.svc/imagecontent/1/TMG10811028/m/Miranda_Kerr_2902539a.jpg" class = "img-rounded center-block"  width="240" height="320">
            </div>
            <div class = "row">
                <!-- change the dummy name and surname -->
                <h4 class="text-center">Miranda Kerr</h4>

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

</body>
</html>