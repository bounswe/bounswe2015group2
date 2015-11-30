<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/recipe-form-style.css"/>
<div class="container text-center">

    <div class="row">
        <div class="col-md-8 col-sm-10 col-xs-12 col-md-offset-2 col-sm-offset-1">
            <div class="panel panel-default">

                <div class="panel-heading clearfix">
                    <h3 class="panel-title">Add a New Menu</h3>
                </div>

                <div class="panel-body">
                    <form class="form-horizontal row-border" action="${contextPath}/menu/${action_type}" method="post">

                        <div class="form-group">
                            <label class="col-md-2 control-label">Menu Name</label>

                            <div class="col-md-10">
                                <input class="form-control" type="text" name="menu_name" value="${existing_menu_name}">
                            </div>
                        </div>


                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">Recipes</div>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <table class="table" id="recipes">

                                        <c:forEach var="recipe" items="${recipeResults}" varStatus="roop">

                                            <tr id= ${recipe.id}>
                                                <td style="display: none">
                                                    <div class="input-group">
                                                        <input type="hidden" name="recipe_id" value="${recipe.id}">
                                                        <input type="hidden" name="recipe_name" value="${recipe.name}">
                                                        <input type="hidden" name="recipe_included" value="false">
                                                    </div>
                                                </td>
                                                <td>${recipe.name}</td>
                                                <td>
                                                        <span class="btn btn-default add_button" style="display: inline"
                                                              onclick="clickAdd(${recipe.id})">Add!</span>
                                                        <span class="btn btn-success" style="display: none"
                                                              onclick="clickAdded(${recipe.id})">Added</span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>

                </div>


                <%--SUBMIT--%>
                <div class="form-group">
                    <button type="submit" class="btn btn-success"
                            style="text-transform: capitalize">${action_type}</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    function clickAdd(id) {
        document.getElementById(id).querySelector("input[name=recipe_included]").setAttribute("value", "true");
        document.getElementById(id).querySelector(".btn-default").setAttribute("style", "display: none");
        document.getElementById(id).querySelector(".btn-success").setAttribute("style", "display: inline");
    }
    function clickAdded(id) {
        document.getElementById(id).querySelector("input[name=recipe_included]").setAttribute("value", "false");
        document.getElementById(id).querySelector(".btn-default").setAttribute("style", "display: inline");
        document.getElementById(id).querySelector(".btn-success").setAttribute("style", "display: none");
    }
</script>
