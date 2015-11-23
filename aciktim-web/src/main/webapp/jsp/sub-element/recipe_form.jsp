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

                        <%-- LOOK FOR --%>

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

                                        <%--LOOK FOR TABLE ROWS--%>

                                    </table>
                                </div>





                            </div>



                        </div>








                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title" >Ingredients</div>
                            </div>
                            <div class="panel-body">

                                <div class="row">
                                    <div id="ingredients">



                                    </div>
                                </div>

                                <div class="row">

                                    <div class="col-xs-12">

                                        <span class="btn btn-default" id="add_ingredient_button">Add Ingredient</span>
                                        <span class="btn btn-default" id="remove_ingredient_button">Remove Ingredient</span>
                                    </div>

                                </div>



                            </div>



                        </div>



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

<script>
    $(document).ready(function() {
        var before = "http://api.nal.usda.gov/ndb/search/?format=json&q=";
        var after = "&sort=r&max=10&offset=0&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX";


        function lookfor(q){
            var fullurl = before + q + after;
            var response = null;
            $.getJSON(fullurl,function(data){
                response = data["list"]['item'];
                displayfoodlist(response);
            });
        }


        $("#search").click(function(){
            var query = $("#query").val();
            lookfor(query);
        });

        function displayfoodlist(food_list){
            $(".table_row_instance").remove();
            $.each(food_list, function(i, val) {
                var table_row = $('#table_row_template').clone();

                table_row.children().first().text(val['name']);
                table_row.children().find("#add_button").attr("data-ndb-num" , val['ndbno']);
                table_row.children().find("#add_button").attr("data-food-name" , val['name']);
                table_row.attr("class" , "table_row_instance");

                $(".table").append(table_row);
            });
        }

        function generateIngredientHtml(num) {
            num;
            var template = $("#ingredient_template").clone();
            var label   = "Ingredient "+num;
            var unit    = "ingredient_units["+num+"]";
            var name    = "ingredient_names["+num+"]";
            var amount  = "ingredient_amounts["+num+"]";

            template.find(".control-label").text(label);
            template.find("#name").attr("name" , name);
            template.find("#amount").attr("name" , amount);
            template.find("#unit").attr("name" , unit);
            template.removeAttr('style');
            template.attr("id" , "ingredient");

            return template;

        }

        var x = 1; //initial text box count
        var max_fields          = 10; //maximum input boxes allowed
        var wrapper             = $("#ingredients"); //Fields wrapper
        var add_button          = $("#add_ingredient_button"); //Add button ID
        var remove_button       = $("#remove_ingredient_button"); //Add button ID

        var firstInsertion= generateIngredientHtml(x);
        wrapper.append(firstInsertion);



        $(add_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                var tobeinserted = generateIngredientHtml(x);
                wrapper.append(tobeinserted);

            }
        });

        $(remove_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x > 1){ //max input box allowed
                x--; //text box increment
                wrapper.children().last().remove();
            }
        });

        $("#look_for").click(function() {
            $("#look_for_panel_body").slideToggle( "slow" );
        });




    });

</script>



<%-- TEMPLATES --%>


<div class="form-group" id="ingredient_template" style="display: none">

    <div class="col-sm-3">
        <label class="control-label">Ingredient </label>
    </div>

    <div class="col-sm-3">
        <div class="input-group">
            <span class="input-group-addon">Name</span>
            <input type="text" class="form-control" placeholder="e.g. Sugar" id="name" readonly="">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group">
            <span class="input-group-addon">Amount</span>
            <input type="text" class="form-control" id="amount" readonly="">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group">
            <select class="form-control" title="Select Unit" id="unit" readonly="">
                <option value="ml">Mililiters</option>
                <option value="gr">Grams</option>
                <option value="qt">Quantity</option>
            </select>

        </div>
    </div>
</div>


<table style="display: none;">
    <tr id="table_row_template">
        <td>

        </td>
        <td>
            <span class="btn btn-default" id="add_button" >Add!</span>
        </td>
    </tr>
</table>
