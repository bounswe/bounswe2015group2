<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/recipe-form-style.css"/>
<div class="container text-center">

    <div class="row">
        <div class="col-md-8 col-sm-10 col-xs-12 col-md-offset-2 col-sm-offset-1">
            <div class="panel panel-default">

                <div class="panel-heading clearfix">
                    <h3 class="panel-title">Add a New Recipe</h3>
                </div>

                <div class="panel-body">
                    <form class="form-horizontal row-border" action="${contextPath}/recipe/${action_type}" method="post">

                        <div class="form-group">
                            <label class="col-md-2 control-label">Recipe Name</label>
                            <div class="col-md-10">
                                <input class="form-control" type="text" name="recipe_name" value="${existing_recipe_name}" placeholder="e.g. Şakşuka">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">Image Url</label>
                            <div class="col-md-10">
                                <input class="form-control" type="text" name="image_url" value="${existing_recipe_image_url}" placeholder="http://someimageurl.png">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">Recipe Description</label>
                            <div class="col-md-10">
                                <textarea class="form-control" name="description" rows="10">${existing_recipe_description}</textarea>
                            </div>
                        </div>

                        <%-- Look For Panel--%>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title btn btn-default"  id="look_for">Look For Ingredients</div>
                            </div>

                            <%-- LOOK FOR BODY --%>
                            <div class="panel-body" id="look_for_panel_body">

                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-2 ">
                                        <input class="form-control" type="text" placeholder="Armut" id="query">
                                    </div>
                                    <div class="col-sm-2">
                                        <span class="btn btn-primary" id="search">Search!</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>Food Name</th>
                                        </tr>
                                        </thead>
                                        <%--Place to insert table rows--%>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <%--Choosen Ingredients Panel--%>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title" >Ingredients</div>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div id="ingredients">
                                        <%--Place to insert ingredient--%>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--Choosen Tags Panel--%>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title" >Tags</div>
                            </div>
                            <div class="panel-body">
                                <div class="row" >
                                    <div class="col-sm-12" id="tags">

                                    </div>
                                    <%--<div id="tags">--%>
                                    <%--Place to insert tag--%>
                                    <%--</div>--%>

                                </div>
                                <div class="row">
                                    <span class="btn btn-default" id="add_tag">Add Tag</span>
                                    <span class="btn btn-default" id="remove_tag">Remove Tag</span>
                                </div>
                            </div>
                        </div>

                        <%--SUBMIT--%>
                        <div class="form-group">
                            <input type="hidden" name="recipe_id" value="${existing_recipe_id}">
                            <button type="submit" class="btn btn-success" style="text-transform: capitalize">${action_type}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>





<%------------------------- TEMPLATES ---------------------------%>



<%--------- Ingredient (Look For Ingrendient) -------------%>
<div class="form-group" id="ingredient_template" style="display: none">

    <div class="col-sm-2">
        <label class="control-label">Ingredient </label>
    </div>

    <div class="col-sm-6">
        <%--<div class="input-group" style="width: inherit;">--%>
        <input type="text" class="form-control"  id="name"   readonly>
        <%--</div>--%>
    </div>

    <div class="col-sm-3">
        <div class="input-group">
            <input type="hidden" id="ndb_no">

            <input type="hidden" id="en" name="ingredient_en">
            <input type="hidden" id="carb" name="ingredient_carb">
            <input type="hidden" id="prot" name="ingredient_prot">
            <input type="hidden" id="fat" name="ingredient_fat">

            <input type="text" class="form-control" id="amount">
            <span class="input-group-addon">grams</span>
        </div>
    </div>

    <div class="col-sm-1">
        <button type="button" class="btn btn-default " id="rm_button">X</button>
    </div>
</div>

<%--------- Table Row (On Choosen Ingredients) -------------%>
<table style="display: none;">
    <tr id="table_row_template">
        <td>

        </td>
        <td>
            <span class="btn btn-default add_button"  >Add!</span>
        </td>
    </tr>
</table>



<%--------- Tag (On Tags) -------------%>

<%--<div class="form-group"  id="tag_template" style="display: none ; margin-top: 10px;">--%>
<div class="row" id="tag_template" style="display: none ; margin-top: 10px;">
    <div class="col-sm-4 col-sm-offset-4">
        <div class="form-group">
            <input type="text" class="form-control">
        </div>
    </div>
</div>



<script type="text/javascript" src="${contextPath}/assets/js/recipe_form.js"></script>