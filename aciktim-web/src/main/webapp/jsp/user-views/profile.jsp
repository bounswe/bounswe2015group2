<%@include file="../sub-element/jsp_imports.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../sub-element/imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">


    <c:if test="${full_name == ''}">
        <%@include file="../sub-element/header_signed_out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include file="../sub-element/header_signed_in.jsp" %>
    </c:if>


    <c:if test="${full_name == ''}">
        <%@include file="../sub-element/content_bar_signed_out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include file="../sub-element/content_bar_signed_in.jsp" %>
    </c:if>

    <div class="container">

        <div class="row">
            <div class="col-md-3">

                <div class="row">
                    <h3>My Profile</h3>
                </div>

                <div class="row">
                    <!-- change the dummy link -->
                    <img src=${picture_url} class="img-rounded center-block" width="240" height="320">
                </div>
                <div class="row">
                    <!-- change the dummy name and surname -->
                    <h4 class="text-center">${full_name}</h4>

                    <table class="table">
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
                        <tr>
                            <td colspan="2" align="center" valign="middle">
                                <a class="btn btn-success" href="${contextPath}/recipes?ownerID=${user_id}">My
                                    Recipes</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="col-md-9">
                <div class="row">
                    <h4>Recommendations</h4>
                </div>
                <div class="row">
                    <ul class="nav nav-tabs">
                        <li class="nav active"><a href="#basedPref" data-toggle="tab">Based on Preferences</a></li>
                        <li class="nav"><a href="#basedDaily" data-toggle="tab">Based on Daily Nutrition</a></li>
                    </ul>
                    <div class="tab-content">

                        <div class="tab-pane fade in active" id="basedPref">
                            <table class="table table-striped" style="margin-top:10px;">
                                <thead>
                                <th>Name</th>
                                <th>View</th>
                                </thead>
                                <tbody>
                                <c:forEach var="recipe" items="${recommendations_preferences}" varStatus="roop">
                                    <tr>
                                        <td width="20%">${recipe.name}</td>
                                        <td>
                                            <form class="form-horizontal row-border" action="${contextPath}/recipe/single"
                                                  method="post">
                                                <input type="hidden" name="recipe_id" value="${recipe.id}"/>
                                                <button type="submit" class="btn btn-default"
                                                        style="text-transform: capitalize">View
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="basedDaily">
                            <table class="table table-striped" style="margin-top:10px;">
                                <thead>
                                <th>Name</th>
                                <th>View</th>
                                </thead>
                                <tbody>
                                <c:forEach var="recipe" items="${recommendations}" varStatus="roop">
                                    <tr>
                                        <td width="20%">${recipe.name}</td>
                                        <td>
                                            <form class="form-horizontal row-border" action="${contextPath}/recipe/single"
                                                  method="post">
                                                <input type="hidden" name="recipe_id" value="${recipe.id}"/>
                                                <button type="submit" class="btn btn-default"
                                                        style="text-transform: capitalize">View
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <h4>Preferences</h4>
                </div>
                <div class="row">
                    <%--<ul class="nav nav-tabs">--%>
                    <%--<li role="presentation" class="active"><a href="#">Likes</a></li>--%>
                    <%--<li role="presentation"><span>Dislikes</span></li>--%>
                    <%--<li role="presentation"><a href="#">Allergies</a></li>--%>
                    <%--</ul>--%>
                    <form action="${contextPath}/user/preferences/save" method="post">
                        <ul class="nav nav-tabs">
                            <li class="nav active"><a href="#like" data-toggle="tab">Likes</a></li>
                            <li class="nav"><a href="#dislike" data-toggle="tab">Dislikes</a></li>
                            <li class="nav"><a href="#allergy" data-toggle="tab">Allergies</a></li>
                            <button type="submit" class="btn btn-default">Save Preferences!</button>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">

                            <div class="tab-pane fade in active" id="like">
                                <div class="form-group">
                                    <input type="text" name="likes" value="${likes}" data-role="tagsinput"/>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="dislike">
                                <div class="form-group">
                                    <input type="text" name="dislikes" value="${dislikes}" data-role="tagsinput"/>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="allergy">
                                <div class="form-group">
                                    <input type="text" name="allergies" value="${allergies}" data-role="tagsinput"/>
                                </div>
                            </div>
                        </div>
                    </form>








                    <%--<div class="col-md-6">--%>
                    <%--<form role="form">--%>
                    <%--<div class="form-group">--%>
                    <%--<input type="text" class="form-control" placeholder="Enter a preference">--%>
                    <%--</div>--%>
                    <%--</form>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-3">--%>
                    <%--<button type="button" class="btn btn-warning btn-block">Add</button>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-3">--%>
                    <%--<button type="button" class="btn btn-warning btn-block">See Daily Consumed List</button>--%>
                    <%--</div>--%>
                </div>
                <%--<div class="row">--%>
                <%--<form role="form">--%>
                <%--<div class="form-group">--%>
                <%--<textarea class="form-control" rows="3" placeholder="Some tags"></textarea>--%>
                <%--</div>--%>
                <%--</form>--%>
                <%--</div>--%>

            </div>
        </div>


    </div>
    <!-- /container -->


    <%@include file="../sub-element/footer.jsp" %>

</div>


</body>

<script>
    $(document).ready(function () {

    });


</script>

</html>
