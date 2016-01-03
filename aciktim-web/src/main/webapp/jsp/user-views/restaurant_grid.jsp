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
                </thead>
                <tbody>

                    <%--all restaurants by default--%>
                    <c:forEach var="restaurant" items="${restaurants}" varStatus="roop">
                        <tr>
                            <td>
                                <img src="http://vignette3.wikia.nocookie.net/starwars/images/c/c1/Luke_on_Endor.jpg" class="img-rounded center-block" width="240">
                            </td>
                            <td width="80%">${restaurant.full_name}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>