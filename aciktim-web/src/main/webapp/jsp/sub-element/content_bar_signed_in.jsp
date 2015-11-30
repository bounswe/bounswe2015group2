<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container" id="content-bar">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/custom_style/content-bar-style.css"/>
    <div class="row">
        <div class="col-md-8 text-left">
            <ul class="nav nav-pills">

                <li role="presentation" id="content_bar_profile" ><a href="/aciktim/user/view">Profile</a></li>
                <li role="presentation" id="content_bar_recipes"><a href="/aciktim/recipes">Recipes</a></li>
                <li role="presentation" id="content_bar_restaurants"><a href="/aciktim/restaurants">Restaurants</a></li>
                <li role="presentation" id="content_bar_create_recipe"><a href="/aciktim/recipe/form?action_type=add">Create Recipe</a></li>
                <li role="presentation" id="content_bar_create_menu"><a href="/aciktim/menu/form?action_type=add">Create Menu</a></li>
                <li role="presentation" id="content_bar_menus"><a href="/aciktim/menus">My Menus</a></li>
                <li role="presentation" id="content_bar_dailyconsuption"><a href="/aciktim/user/dailyconsupmtion">Daily Consumption</a></li>
            </ul>
        </div>

        <div class="col-md-4 text-right">
            <form action="${contextPath}/recipes" method="post" class="navbar-form">
                <div class="form-group">
                    <input type="text" name="search_keyword" class="form-control search_group" id="search-field" placeholder="Search" id="content_bar_input">
                    <button type="submit" class="btn btn-warning search_group" id="content_bar_search">Search</button>
                </div>
                <button type="button" class="btn btn-warning search_group" id="content_bar_advanced">Advanced Search</button>
            </form>
        </div>

    </div>

</div>


<script>
    $(document).ready(function(){

        var selection = "${content_bar_selection}";
        $("li").removeAttr("class");
        $("#content_bar_"+selection).attr("class","active");


        if(selection == "profile"){
            $(".search_group").css("display","none");
        }else if(selection == "recipes"){
            $(".search_group").css("display","");
        }else if(selection == "create_recipe"){
            $(".search_group").css("display","");
        }else{
            $(".search_group").css("display","");
            $("#content_bar_advanced").css("display","none");
        }






        $("#content_bar_advanced").click(function(){
            $("#searchForm").show();
        });

    });
</script>

<div class="row" style="background-color:#fff1c6 "  >
    <form action="${contextPath}/recipes" class="form-horizontal" id="searchForm" hidden="hidden" method="post" class="navbar-form">

        <div class="col-xs-6 col-md-4">
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label >Name</label>
                </div>
                <div class="col-xs-12 col-md-8">
                    <input type="text" name="search_keyword" class="form-control" placeholder="Pizza"  id="search_keyword">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label >Ingredients</label>
                </div>
                <div class="col-xs-12 col-md-8">
                    <input type="text" name="ingredients_string" class="form-control" placeholder="Meat, Egg, Onion" id="ingredients_string">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label>Made At</label><br>
                </div>
                <div class="col-xs-6 col-md-4">
                    <input type="radio" name="madeWhere" value="home">Home<br>
                </div>
                <div class="col-xs-6 col-md-4">
                    <input type="radio" name="madeWhere" value="restaurant">Restaurant
                </div>
            </div>
        </div>

        <div class="col-xs-5 col-md-3">
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label  >Carbohydrate</label>
                </div>
                <div class="col-xs-12 col-md-8">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="100" id="carbon">
                        <span class="input-group-addon">g</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label >Fat</label>
                </div>
                <div class="col-xs-12 col-md-8">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="100" id="fat">
                        <span class="input-group-addon">g</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label >Protein</label>
                </div>
                <div class="col-xs-12 col-md-8">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="100" id="protein">
                        <span class="input-group-addon">g</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-md-4">
                    <label >Calories</label>
                </div>
                <div class="col-xs-12 col-md-8">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="100" id="calories">
                        <span class="input-group-addon">g</span>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-xs-7 col-md-5">
            <div class="col-xs-9 col-md-6">
                <label>Popular Tags <span class="glyphicon glyphicon-tags"></span></label><br>
                <div id="c_b">
                    <input type="checkbox" name="tag_cheap" value="Cheap" >Cheap<br>
                    <input type="checkbox" name="tag_fat_free" value="Fat-free">Fat-free<br>
                    <input type="checkbox" name="tag_quick" value="Quick">Quick<br>
                    <input type="checkbox" name="tag_for_guest" value="Forguest">For guest<br>
                </div>
            </div>
            <div class="col-xs-9 col-md-6">
                <label>Enter Tags </label><br>
                <textarea id="t" placeholder="Tag1, Tag2, Tag3"></textarea>
                <button type="submit" id="searchAlert" class="btn btn-warning search_group"><span class="glyphicon glyphicon-search"></span>Search</button>
            </div>
            <script>
                function updateTextArea() {
                    var allVals = [];
                    $('#c_b :checked').each(function() {
                        allVals.push($(this).val());
                    });
                    $("#t").val(allVals);
                }
                $(function() {
                    $("#c_b input").click(updateTextArea);
                    updateTextArea();
                });
            </script>
        </div>
    </form>
</div>