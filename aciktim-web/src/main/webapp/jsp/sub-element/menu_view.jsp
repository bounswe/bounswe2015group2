<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/recipe-form-style.css"/>


<div class="container">
    <%--<div class="row">--%>
    <%--<div class="col-sm-12">--%>

    <%--<form action="${contextPath}/recipeform" method="post" class="form-group">--%>
    <%--<input type="hidden" name="action_type" value="add">--%>
    <%--<button type="submit" class="btn btn-primary">Add a new recipe</button>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--</div>--%>


    <div class="row">
        <div class="col-sm-12">
            <table class="table table-bordered" style="margin-top:10px;">
                <thead>
                <th>Name</th>
                <th>Recipes</th>
                </thead>
                <tbody>

                <%--my recipes by default--%>
                <c:forEach var="menu" items="${menus}" varStatus="roop">
                    <tr>
                        <td>
                                ${menu.name}
                        </td>
                        <td>
                            <c:forEach var="recipe" items="${menu.recipes}" varStatus="roop">

                                <table class="table" style="margin-top:10px;">
                                    <tr><td>${recipe.name}</td></tr>
                                </table>
                            </c:forEach>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</div>


