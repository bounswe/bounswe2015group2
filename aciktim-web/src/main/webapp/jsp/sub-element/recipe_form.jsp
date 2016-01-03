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
                    <form class="form-horizontal row-border" id="add_recipe_form" action="${contextPath}/recipe/${action_type}" method="post">

                        <div class="form-group">
                            <label class="col-md-2 control-label">Recipe Name</label>
                            <div class="col-md-10">
                                <input class="form-control" type="text" name="recipe_name" value="${existing_recipe_name}" placeholder="e.g. Şakşuka" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">Image Url</label>
                            <div class="col-md-10">
                                <input class="form-control" type="text" name="image_url" value="${existing_recipe_image_url}" placeholder="http://someimageurl.png" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">Recipe Description</label>
                            <div class="col-md-10">
                                <textarea class="form-control" name="description" rows="10" required>${existing_recipe_description}</textarea>
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
                                        <input class="form-control" type="text" placeholder="Pear" id="query">
                                    </div>
                                    <div class="col-sm-2">
                                        <span class="btn btn-primary" id="search">Search!</span>
                                    </div>
                                </div>

                                <div class="row">
                                        <table class="table table-striped col-sm-12"style="margin-bottom: 0px">
                                            <thead>
                                            <tr>
                                                <th>Food Name</th>
                                            </tr>
                                            </thead>
                                            <tbody id="searchedingredients">

                                            </tbody>


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
                                    <div id="addedingredients">
                                        <%--Place to insert added ingredient--%>
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
                                        <%--Place to insert tags--%>
                                    </div>


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



<%--------- Added Ingredient Template -------------%>
<div class="form-group" id="added_ingredient_template" style="display: none">

    <div class="col-sm-2">
        <label class="control-label">Ingredient </label>
    </div>

    <div class="col-sm-3">
        <input type="text" class="form-control"  id="name"   name="ingredient_name" readonly>
    </div>

    <div class="col-sm-2">
        <div class="input-group">
            <input type="hidden" id="ndbno" name="ingredient_ndbno" >
            <input type="hidden" id="en" name="ingredient_en">
            <input type="hidden" id="carb" name="ingredient_carb">
            <input type="hidden" id="prot" name="ingredient_prot">
            <input type="hidden" id="fat" name="ingredient_fat">

            <input type="number" class="form-control" id="amount" name="ingredient_amount" placeholder="Quantity" required>
        </div>
    </div>
    <div class="col-sm-3">
        <select class="form-control" id="unit" name="ingredient_unit"></select>

        <%--<input type="text" class="form-control" id="unit" name="ingredient_unit" readonly>--%>
    </div>


    <div class="col-sm-2">
        <button type="button" class="btn btn-default rm_button">X</button>
    </div>
</div>



<%--------- Searched Ingredient Template -------------%>
<table style="display: none;">
    <tr id="searched_ingredient_template">
        <td style="vertical-align: middle"></td>
        <td>
            <span class="btn btn-default add_button"  >Add!</span>
        </td>
    </tr>
</table>



<%--------- Tag Template -------------%>
<div class="form-inline" id="tag_template" style="display: none ; margin-top: 10px;">

    <div class="form-group" style="margin-left: 0px; margin-right: 0px; margin-bottom: 10px">
        <label class="col-md-4 control-label">Tag Name   </label>
        <div class="col-md-8">
            <input type="text" class="form-control" id="one" name="tag_name" required>
        </div>
    </div>

    <div class="form-group" style="margin-left: 0px; margin-right: 0px; margin-bottom: 10px">
        <label class="col-md-4 control-label">Tag Class   </label>
        <div class="col-md-8">
            <input type="text" class="form-control" id="two" name="tag_class" value="">
        </div>
    </div>

</div>




<script type="text/javascript" src="${contextPath}/assets/js/recipe_form.js"></script>