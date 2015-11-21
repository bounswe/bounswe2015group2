<%@include file="../sub-element/jsp_imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../sub-element/imports.jsp"%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/home-index-style.css"/>
</head>
<body>
<div class="container" id="main-container">

    <c:if test="${full_name == ''}">
        <%@include  file="../sub-element/header_signed_out.jsp" %>
    </c:if>
    <c:if test="${full_name != ''}">
        <%@include  file="../sub-element/header_signed_in.jsp" %>
    </c:if>

    <%@include file="../sub-element/content_bar.jsp"%>
    <c:if test="${full_name != ''}">



        <div class="container">
            <div class="row">
                <div class="col-sm-12">

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

    <c:if test="${full_name == ''}">
        <%@include  file="../sub-element/login_to_see.jsp" %>
    </c:if>

    <%@include  file="../sub-element/footer.jsp" %>

</div>

</body>
</html>