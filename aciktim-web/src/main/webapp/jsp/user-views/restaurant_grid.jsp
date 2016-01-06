<%@include file="../sub-element/jsp_imports.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../sub-element/imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">


    <div class="row">
        <div class="col-sm-12">
            <table class="table table-bordered" style="margin-top:10px;">
                <thead>
                <th>Picture</th>
                <th>Name</th>
                <%--<th>View</th>--%>
                </thead>
                <tbody>

                    <%--all restaurants by default--%>
                    <c:forEach var="restaurant" items="${restaurantResults}" varStatus="roop">
                        <tr>
                            <td width="20%">
                                <img src="http://vignette3.wikia.nocookie.net/starwars/images/c/c1/Luke_on_Endor.jpg" class="img-rounded center-block" width="240">
                            </td>
                            <td><a href="${contextPath}/restaurant/single?restaurant_id=${restaurant.id}">${restaurant.full_name}</td>
                            <%--<td>--%>
                                <%--<form class="form-horizontal row-border" action="${contextPath}/restaurant/single">--%>
                                    <%--<input type="hidden" name="restaurant_id" value="${restaurant.id}"/>--%>
                                    <%--<button type="submit" class="btn btn-default"--%>
                                            <%--style="text-transform: capitalize">View</button>--%>
                                <%--</form>--%>
                            <%--</td>--%>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>