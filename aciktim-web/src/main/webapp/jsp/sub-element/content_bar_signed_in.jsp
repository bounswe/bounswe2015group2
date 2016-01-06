<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>

<div class="container">
    <div class="row" id="content-bar">
        <div class="col-md-8 text-left">
            <ul class="nav nav-pills" >

                <li role="presentation" id="content_bar_profile" ><a href="/aciktim/user/view">Profile</a></li>
                <li role="presentation" id="content_bar_recipes"><a href="${contextPath}/recipes">Recipes</a></li>
                <li role="presentation" id="content_bar_restaurants"><a href="/aciktim/restaurants">Restaurants</a></li>
                <li role="presentation" id="content_bar_create_recipe"><a href="/aciktim/recipe/form?action_type=add">Create Recipe</a></li>
                <li role="presentation" id="content_bar_create_menu"><a href="/aciktim/menu/form?action_type=add">Create Menu</a></li>
                <li role="presentation" id="content_bar_menus"><a href="/aciktim/menus">My Menus</a></li>
                <li role="presentation" id="content_bar_dailyconsumption"><a href="/aciktim/user/dailyconsumption"> Consumption</a></li>
            </ul>
        </div>

        <div class="col-md-4 text-right">
            <form action="${contextPath}/recipes" method="get" class="navbar-form">
                <div class="form-group">
                    <input type="text" name="keyword" class="form-control search_group" id="search-field" placeholder="Search" style="display: none">
                    <button type="submit" class="btn btn-warning search_group" id="content_bar_search" style="display: none">Search</button>
                </div>
                <button type="button" class="btn btn-warning search_group" id="content_bar_advanced" style="display: none">Advanced Search</button>
            </form>
        </div>
    </div>


    <div class="row" style="background-color:#F5E599; border-radius: 5px"  >
        <div class="col-sm-12">
            <form action="${contextPath}/recipes" class="form-horizontal" id="searchForm" hidden="hidden" method="get" class="navbar-form">
                <div class="row" style="margin: 10px">
                    <div class="col-sm-3">
                        <div class="row">
                            <label>Recipe Name</label><br>
                            <input type="text" class="form-control" placeholder="Lasagne"  id="keyword" name="keyword">
                        </div>
                        <div class="row"style="margin-top: 10px">
                            <label>Made At</label><br>
                            <input type="radio" name="madeAt" value="home"> Home
                            <input type="radio" name="madeAt" value="restaurant" style="margin-left: 10px"> Restaurant
                        </div>
                        <div class="row text-center" style="margin-top: 12px">
                            <button type="submit" id="search" class="btn btn-warning search_group"><span class="glyphicon glyphicon-search"></span>Search</button>
                        </div>
                    </div>


                    <div class="col-sm-3">
                        <label>Set Nutritional Bounds</label><br>
                        <div class="row" >
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon">Max</span>
                                    <input type="text" class="form-control" placeholder="Carbo" aria-describedby="sizing-addon3" id="carbUpper" name="carbUpper">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <input type="text" class="form-control" placeholder="Carbo" aria-describedby="sizing-addon3" id="carbLower" name="carbLower">
                                    <span class="input-group-addon">Min</span>
                                </div>
                            </div>
                        </div>
                        <div class="row"style="margin-top: 5px">
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon">Max</span>
                                    <input type="text" class="form-control" placeholder="Protein" aria-describedby="sizing-addon3" id="protUpper" name="protUpper">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <input type="text" class="form-control" placeholder="Protein" aria-describedby="sizing-addon3" id="protLower" name="protLower">
                                    <span class="input-group-addon">Min</span>
                                </div>
                            </div>
                        </div>
                        <div class="row"style="margin-top: 5px">
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon" >Max</span>
                                    <input type="text" class="form-control" placeholder="Fat" aria-describedby="sizing-addon3" id="fatUpper" name="fatUpper">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <input type="text" class="form-control" placeholder="Fat" aria-describedby="sizing-addon3" id="fatLower" name="fatLower">
                                    <span class="input-group-addon">Min</span>
                                </div>
                            </div>
                        </div>
                        <div class="row"style="margin-top: 5px">
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon">Max</span>
                                    <input type="text" class="form-control" placeholder="Calories" aria-describedby="sizing-addon3" id="calUpper" name="calUpper">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-sm">
                                    <input type="text" class="form-control" placeholder="Calories" aria-describedby="sizing-addon3" id="calLower" name="calLower">
                                    <span class="input-group-addon">Min</span>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="col-sm-3">
                        <label>Enter Ingredients</label><br>
                        <input type="text" class="form-control" id="ingredients" value="onions,garlic,bread" name="ingredients"/>

                    </div>
                    <div class="col-sm-3">
                        <label>Enter Tags <span class="glyphicon glyphicon-tags"></span></label><br>
                        <input type="text" class="form-control" id="tags" value="pasta,fast" name="tags"/>

                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<%--<script>--%>
<%--function updateTextArea() {--%>
<%--var allVals = [];--%>
<%--$('#c_b :checked').each(function() {--%>
<%--allVals.push($(this).val());--%>
<%--});--%>
<%--$("#t").val(allVals);--%>
<%--}--%>
<%--$(function() {--%>
<%--$("#c_b input").click(updateTextArea);--%>
<%--updateTextArea();--%>
<%--});--%>
<%--</script>--%>





<script>
    $(document).ready(function(){

        var selection = "${content_bar_selection}";
        var isInst = "${isInst}";



        $("li").removeAttr("class");
        $("#content_bar_"+selection).attr("class","active");

        if(selection == "recipes"){
            $(".search_group").css("display","");
        }else if(selection == "restaurants"){
            $(".search_group").css("display","");
            $("#content_bar_advanced").css("display","none");
        }else{
            $(".search_group").css("display","none");
        }

        $("#content_bar_advanced").click(function(){
            $("#searchForm").toggle("fast",null);
        });

        $('#tags').tokenfield();
        $('#ingredients').tokenfield();

        $('#search').on('submit', function(){
            if($('#keyword').val().length == 0) $('#keyword').disable();
            if($('#carbUpper').val().length == 0) $('#carbUpper').disable();
            if($('#carbLower').val().length == 0) $('#carbLower').disable();
            if($('#protUpper').val().length == 0) $('#protUpper').disable();
            if($('#protLower').val().length == 0) $('#protLower').disable();
            if($('#fatUpper').val().length == 0) $('#fatUpper').disable();
            if($('#fatLower').val().length == 0) $('#fatLower').disable();
            if($('#calUpper').val().length == 0) $('#calUpper').disable();
            if($('#calLower').val().length == 0) $('#calLower').disable();
            if($('#ingredients').val().length == 0) $('#ingredients').disable();
            if($('#tags').val().length == 0) $('#tags').disable();
        });


    });
</script>
















<%--<div class="row" style="margin-top: 10px">--%>
<%--<div class="col-sm-4">--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label >Name</label>--%>
<%--</div>--%>
<%--<div class="col-xs-12 col-md-8">--%>
<%--<input type="text" name="search_keyword" class="form-control" placeholder="Pizza"  id="search_keyword">--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label >Ingredients</label>--%>
<%--</div>--%>
<%--<div class="col-xs-12 col-md-8">--%>
<%--<input type="text" name="ingredients_string" class="form-control" placeholder="Meat, Egg, Onion" id="ingredients_string">--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label>Made At</label><br>--%>
<%--</div>--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<input type="radio" name="madeAt" value="home">Home<br>--%>
<%--</div>--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<input type="radio" name="madeAt" value="restaurant">Restaurant--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="col-sm-3">--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label  >Carbohydrate</label>--%>
<%--</div>--%>
<%--<div class="col-xs-12 col-md-8">--%>
<%--<div class="input-group">--%>
<%--<input type="text" class="form-control" placeholder="100" id="carbo" name="carbo">--%>
<%--<span class="input-group-addon">g</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label >Fat</label>--%>
<%--</div>--%>
<%--<div class="col-xs-12 col-md-8">--%>
<%--<div class="input-group">--%>
<%--<input type="text" class="form-control" placeholder="100" id="fat" name="fat">--%>
<%--<span class="input-group-addon">g</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label >Protein</label>--%>
<%--</div>--%>
<%--<div class="col-xs-12 col-md-8">--%>
<%--<div class="input-group">--%>
<%--<input type="text" class="form-control" placeholder="100" id="protein" name="protein">--%>
<%--<span class="input-group-addon">g</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<label >Calories</label>--%>
<%--</div>--%>
<%--<div class="col-xs-12 col-md-8">--%>
<%--<div class="input-group">--%>
<%--<input type="text" class="form-control" placeholder="100" id="calories" name="calories">--%>
<%--<span class="input-group-addon">g</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>